from kivy.app import App
from kivy.uix.image import *
from kivy.lang import Builder
from kivy.uix.screenmanager import ScreenManager,Screen
from kivy.uix.anchorlayout import AnchorLayout
from kivy.uix.boxlayout import BoxLayout
from kivy.uix.image import Image,AsyncImage
from kivy.uix.label import Label
from imageandmodelhander import *
from kivy.uix.widget import Widget


Builder.load_file("design.kv")

class SignLangInt(Screen):
    def sec_screen(self):
        self.manager.current="Sec_screen"
    
    

class SecScreen(Screen):
    def capture(self):
        camera=self.ids['camera']
        # timestr=time.strftime("%Y%m%d_%H%M%S")
        count=1
        camera.export_to_png("./SLI_{}.png".format(count))
        # img=AsyncImage(source='https://miro.medium.com/max/1050/1*kuqOPIyFNy9P-lLR3JiuMQ.png')
        image_prepocess("SLI_1.png")
        
        print("captured")
        

    def back(self):
        self.manager.current="Sign_lang"
    
    def third_screen(self):
       # predict("SLI_1.png")
        self.manager.current="Third_sec"
    
    

class ThirdScreen(Screen):
    
    def back(self):
        self.manager.current="Sign_lang"
        
    


class RootWidget(ScreenManager):
    pass

class SLIApp(App):
    def build(self):
        return RootWidget()

if __name__=="__main__":        
    SLIApp().run()