package com.shahbaz.news.datamodel

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shahbaz.news.util.Constant.TABLE_NAME
import kotlinx.parcelize.Parcelize


@Entity(tableName = TABLE_NAME)
@Parcelize
data class Article(
    val author: String?,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    @PrimaryKey
    val url: String,
    val urlToImage: String
):Parcelable