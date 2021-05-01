from kivy.app import App                     # this is used  as u have learnt flask to cnfirm a main func to run in kivy module
from kivy.lang import Builder                         # this is used to import design.kv (just as css)
from kivy.uix.screenmanager import ScreenManager   #prajwal this is for switiching betwwen the screens
from kivy.uix.boxlayout import BoxLayout
import time



Builder.load_file('design.kv')

# the screen for viewing the frames 

class CameraClick(BoxLayout):
    def capture(self):
        camera = self.ids['camera']                             #same as video.Catupre(0) the default camera
        timestr = time.strftime("%Y%m%d_%H%M%S")                # rest all for calculting the time per click
        camera.export_to_png("IMG_{}.png".format(timestr))
        print("Captured")                                      # not storing yet ( will talk using open cv )


class TestCamera(App):                              #configure with main function and running 

    def build(self):
        return CameraClick()

if __name__=="__main__":
    TestCamera().run()                              #this same as flask for congiure with main func()