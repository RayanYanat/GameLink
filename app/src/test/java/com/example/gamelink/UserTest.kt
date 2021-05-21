@file:Suppress("DEPRECATION")

package com.example.gamelink

import com.example.gamelink.model.Annonce
import com.example.gamelink.model.ChatMessage
import com.example.gamelink.model.User
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import org.junit.Test

@Suppress("DEPRECATION")
class UserTest {

    @Test
    fun getUserTest() {
        val user = User("testUid", "testUsername", "imgUrl", null)
        assertEquals("testUid", user.uid)
        assertEquals("testUsername", user.username)
        assertEquals("imgUrl", user.profileImageUrl)
        assertNull(user.listAnnonce)
    }

    @Test
    fun setUserTest() {
        val user = User()
        user.uid = ("1111")
        user.username = ("test_username")
        user.profileImageUrl = ("url_picture")
        assertEquals("1111", user.uid)
        assertEquals("test_username", user.username)
        assertEquals("url_picture", user.profileImageUrl)
    }

    @Test
    fun getListAnnounce() {
        val announce = Annonce(
            "game announce",
            "announce text",
            "12/01/21",
            "tittle",
            "username",
            "uid",
            "urlPicture"
        )
        assertEquals("game announce", announce.gameAnnonce)
        assertEquals("announce text", announce.annonceText)
        assertEquals("12/01/21", announce.dateAnnonce)
        assertEquals("tittle", announce.tittle)
        assertEquals("username", announce.username)
        assertEquals("uid", announce.uid)
        assertEquals("urlPicture", announce.urlPicture)
    }

    @Test
    fun setListAnnounce() {
        val announce = Annonce()
        announce.gameAnnonce = ("game announce")
        announce.annonceText = ("announce text")
        announce.dateAnnonce = ("12/01/21")
        announce.tittle = ("tittle")
        announce.username = ("username")
        announce.uid = ("uid")
        announce.urlPicture = ("urlPicture")


        assertEquals("game announce", announce.gameAnnonce)
        assertEquals("announce text", announce.annonceText)
        assertEquals("12/01/21", announce.dateAnnonce)
        assertEquals("tittle", announce.tittle)
        assertEquals("username", announce.username)
        assertEquals("uid", announce.uid)
        assertEquals("urlPicture", announce.urlPicture)
    }

    @Test
    fun getChatMsg() {
        val chatMsg = ChatMessage("id","chat msg","fromId","toId",123321231)
        assertEquals("id", chatMsg.id)
        assertEquals("chat msg", chatMsg.text)
        assertEquals("fromId", chatMsg.fromId)
        assertEquals("toId", chatMsg.toId)
        assertEquals(123321231, chatMsg.timestamp)
    }

    @Test
    fun setChatMsg(){
        val chatMsg = ChatMessage()
        chatMsg.id = ("id")
        chatMsg.text = ("chat msg")
        chatMsg.fromId = ("fromId")
        chatMsg.toId = ("toId")
        chatMsg.timestamp = 123321231

        assertEquals("id", chatMsg.id)
        assertEquals("chat msg", chatMsg.text)
        assertEquals("fromId", chatMsg.fromId)
        assertEquals("toId", chatMsg.toId)
        assertEquals(123321231, chatMsg.timestamp)

    }

}

