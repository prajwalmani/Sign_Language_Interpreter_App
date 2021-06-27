from kivy.app import App
from kivy.lang import Builder
from kivy.uix.screenmanager import ScreenManager,Screen
from kivy.uix.anchorlayout import AnchorLayout
from kivy.uix.boxlayout import BoxLayout
import time

Builder.load_file("design.kv")

class SignLangInt(Screen):
    def sec_screen(self):
        self.manager.current="Sec_screen"

class SecScreen(Screen):
    def capture(self):
        camera = self.ids['camera']                             #same as video.Catupre(0) the default camera
        timestr = time.strftime("%Y%m%d_%H%M%S")                # rest all for calculting the time per click
        camera.export_to_png("IMG_{}.png".format(timestr))
        print("Captured")

class RootWidget(ScreenManager):
    pass

class SLIApp(App):
    def build(self):
        return RootWidget()

if __name__=="__main__":
    SLIApp().run()       