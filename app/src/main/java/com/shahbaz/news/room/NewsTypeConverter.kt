package com.shahbaz.news.room

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.shahbaz.news.datamodel.Source

@ProvidedTypeConverter
class NewsTypeConverter {

    @TypeConverter
    fun sourceToString(source:Source) :String{
        return "${source.name},${source.name}"
    }

    @TypeConverter
    fun stringToSource(source:String):Source{
        return source.split(",").let {
            Source(it[0],it[1])
        }
    }
}