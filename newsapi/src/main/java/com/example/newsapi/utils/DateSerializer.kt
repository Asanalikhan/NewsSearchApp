package com.example.newsapi.utils

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.text.DateFormat
import java.text.spi.DateFormatProvider
import java.util.Date

internal object DateSerializer: KSerializer<Date>{
    override val descriptor = PrimitiveSerialDescriptor("Date", PrimitiveKind.LONG)
    override fun serialize(encoder: Encoder, value: Date) = encoder.encodeString(value.toString())
    override fun deserialize(decoder: Decoder): Date = DateFormat.getDateTimeInstance().parse(decoder.decodeString())
}