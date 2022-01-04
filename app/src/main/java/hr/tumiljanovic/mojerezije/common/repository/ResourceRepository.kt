package hr.tumiljanovic.mojerezije.common.repository

import android.content.res.Resources

interface ResourceRepository {
    fun getString(stringId: Int): String
    fun getResources(): Resources
    fun getStringArray(arrayId: Int): Array<String>
}

