package com.movie.app

import com.movie.app.base.formatGenres
import com.movie.app.base.formatYear
import com.movie.app.data.remote.model.GenreRemoteModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import java.util.*

class ExtensionsTest {

    @Test
    fun `test formatYear success`(){
        val year = "2020"
        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, year.toInt())
        }
        assertEquals(year, formatYear(calendar.time))
    }

    @Test
    fun `test formatYear failure`(){
        val year = "2020"
        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, year.toInt())
        }
        assertNotEquals(year.toInt(), formatYear(calendar.time))
    }

    @Test
    fun `test formatGenres success`(){
        val genres :List<GenreRemoteModel> = List(2){
            GenreRemoteModel("first");
            GenreRemoteModel("first")
        }
        formatGenres(genres).forEach{ it ->
            assertEquals(it, "first")
        }

    }


    @Test
    fun `test formatGenres failure`(){
        val genres :List<GenreRemoteModel> = List(2){
            GenreRemoteModel("first");
            GenreRemoteModel("first")
        }
        formatGenres(genres).forEach{ it ->
            assertNotEquals(it, " first ")
        }
    }

}