from kivy.app import App 
from kivy.uix.boxlayout import  BoxLayout
from kivy.lang import Builder
from kivy.clock import Clock
from jnius import autoclass

Builder.load_file('design2.kv')

class MyRecorder:
    def __init__(self):
        '''Recorder object To access Android Hardware'''
        self.MediaRecorder = autoclass('android.media.MediaRecorder')
        self.AudioSource = autoclass('android.media.MediaRecorder$AudioSource')
        self.OutputFormat = autoclass('android.media.MediaRecorder$OutputFormat')
        self.AudioEncoder = autoclass('android.media.MediaRecorder$AudioEncoder')
 
        # create out recorder
        self.mRecorder = self.MediaRecorder()
        self.mRecorder.setAudioSource(self.AudioSource.MIC)
        self.mRecorder.setOutputFormat(self.OutputFormat.THREE_GPP)
        self.mRecorder.setOutputFile('/sdcard/MYAUDIO.3gp')
        self.mRecorder.setAudioEncoder(self.AudioEncoder.AMR_NB)
        self.mRecorder.prepare()
 
 
 

 
 
 
class AudioTool(BoxLayout):
    def __init__(self, **kwargs):
        super(AudioTool, self).__init__(**kwargs)
        
        self.start_button = self.ids['start_btn']
        self.stop_button = self.ids['stop_btn']
        self.display_label = self.ids['display_label']
        self.switch = self.ids['duration_switch'] # Tutorial 3
        self.user_input = self.ids['usr_input']
            
 
    def enforce_numeric(self): 
        '''Make sure the textinput only accepts numbers'''
        if self.user_input.text.isdigit() == False: 
            digit_list = [num for num in self.user_input.text if num.isdigit()]
            self.user_input.text = "".join(digit_list)
 
    def startRecording_clock(self):
        
        self.mins = 0 #Reset the minutes
        self.zero = 1 # Reset if the function gets called more than once
        self.duration = int(self.user_input.text) #Take the input from the user and convert to a number
        Clock.schedule_interval(self.updateDisplay, 1)
        self.start_button.disabled = True # Prevents the user from clicking start again which may crash the program
        self.stop_button.disabled = False
        self.switch.disabled = True #TUT Switch disabled when start is pressed
        Clock.schedule_once(self.startRecording) ## NEW start the recording 
    
    def startRecording(self, dt): #NEW start the recorder
        self.r = MyRecorder()
        self.r.mRecorder.start()
    
    def stopRecording(self):
    
        Clock.unschedule(self.updateDisplay)
        self.r.mRecorder.stop() #NEW RECORDER VID 6
        self.r.mRecorder.release() #NEW RECORDER VID 6
        
        Clock.unschedule(self.startRecording) #NEW stop the recording of audio VID 6 
        self.display_label.text = 'Finished Recording!'
        self.start_button.disabled = False
        self.stop_button.disabled = True #TUT 3
        self.switch.disabled = False #TUT 3 re enable the switch
         
    def updateDisplay(self,dt):   
        if self.switch.active == False:
            if self.zero < 60 and len(str(self.zero)) == 1:
                self.display_label.text = '0' + str(self.mins) + ':0' + str(self.zero)
                self.zero += 1
                
            elif self.zero < 60 and len(str(self.zero)) == 2:
                    self.display_label.text = '0' + str(self.mins) + ':' + str(self.zero)
                    self.zero += 1
            
            elif self.zero == 60:
                self.mins +=1
                self.display_label.text = '0' + str(self.mins) + ':00'
                self.zero = 1
        
        elif self.switch.active == True:
            if self.duration == 0: # 0
                self.display_label.text = 'Recording Finished!'
                self.stopRecording() # NEW VID 6 / THIS ONE LINE SHOULD TAKE CARE OF THE RECORDING NOT STOPPING. 
                # self.start_button.disabled = False # Re enable start
                # self.stop_button.disabled = True # Re disable stop
                # Clock.unschedule(self.updateDisplay) #DELETE FOR VID 6 
                
                # self.switch.disabled = False # Re enable the switch
                
            elif self.duration > 0 and len(str(self.duration)) == 1: # 0-9
                self.display_label.text = '00' + ':0' + str(self.duration)
                self.duration -= 1
 
            elif self.duration > 0 and self.duration < 60 and len(str(self.duration)) == 2: # 0-59
                self.display_label.text = '00' + ':' + str(self.duration)
                self.duration -= 1
 
            elif self.duration >= 60 and len(str(self.duration % 60)) == 1: # EG 01:07
                self.mins = self.duration / 60
                self.display_label.text = '0' + str(self.mins) + ':0' + str(self.duration % 60)
                self.duration -= 1
 
            elif self.duration >= 60 and len(str(self.duration % 60)) == 2: # EG 01:17
                self.mins = self.duration / 60
                self.display_label.text = '0' + str(self.mins) + ':' + str(self.duration % 60)
                self.duration -= 1


class AudioApp(App):
    def build(self):
        return AudioTool()
 
if __name__ == '__main__':
    AudioApp().run()

