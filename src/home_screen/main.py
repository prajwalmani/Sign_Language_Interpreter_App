from kivy.app import App
from kivy.lang import Builder
from kivy.uix.screenmanager import ScreenManager,Screen
from kivy.uix.anchorlayout import AnchorLayout

Builder.load_file("design.kv")

class SignLangInt(Screen):
    pass

class RootWidget(ScreenManager):
    pass

class SLIApp(App):
    def build(self):
        return SignLangInt()

if __name__=="__main__":
    SLIApp().run()        