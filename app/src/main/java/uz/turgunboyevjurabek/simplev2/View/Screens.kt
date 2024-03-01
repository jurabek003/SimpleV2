package uz.turgunboyevjurabek.simplev2.View

sealed class Screens(val rout:String) {
    object MainClass:Screens("main_class")
    object EditClass:Screens("edit_class")

}