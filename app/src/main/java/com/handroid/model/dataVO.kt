package com.handroid.model

import com.google.gson.annotations.SerializedName

public class dataVO(){
    @SerializedName("title")
    public var title = ""

    @SerializedName("id")
    public var id = ""

    @SerializedName("message")
    public var message = ""

    @SerializedName("documentation_url")
    public var documentation_url = ""

    constructor(title:String, id:String, message:String, documentation_url:String) : this() {
        this.title = title
        this.id = id
        this.message = message
        this.documentation_url = documentation_url
    }


}