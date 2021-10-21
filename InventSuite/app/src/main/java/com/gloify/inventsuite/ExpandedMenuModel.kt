package com.gloify.inventsuite



class ExpandedMenuModel(level: Int) {

    var Text= ""
    var Secondtext=""
    var iconName = ""
    var iconImg = -1 // menu icon resource id

    @JvmName("getText1")
    fun getText(): String? {
        return Text
    }

    @JvmName("setText1")
    fun setText(Text: String) {
        this.Text = Text
    }

    @JvmName("getSecondtext1")
    fun getSecondText(): String {
        return Secondtext
    }

    @JvmName("setSecondtext1")
    fun setSecondText(Secondtext: String) {
        this.Secondtext = Secondtext
    }


    @JvmName("getIconName1")
    fun getIconName(): String? {
        return iconName
    }

    @JvmName("setIconName1")
    fun setIconName(iconName: String) {
        this.iconName = iconName
    }

    @JvmName("getIconImg1")
    fun getIconImg(): Int {
        return iconImg
    }

    @JvmName("setIconImg1")
    fun setIconImg(iconImg: Int) {
        this.iconImg = iconImg
    }

}
