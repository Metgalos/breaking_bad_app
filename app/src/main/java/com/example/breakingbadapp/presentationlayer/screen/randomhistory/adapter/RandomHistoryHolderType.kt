package com.example.breakingbadapp.presentationlayer.screen.randomhistory.adapter

enum class RandomHistoryHolderType {
    FIRST, MIDDLE, LAST, SINGLE, HEADER, FOOTER;

    companion object {
        fun getIntValue(type: RandomHistoryHolderType): Int = type.ordinal
        fun getType(ordinal: Int): RandomHistoryHolderType = values()[ordinal]
    }
}