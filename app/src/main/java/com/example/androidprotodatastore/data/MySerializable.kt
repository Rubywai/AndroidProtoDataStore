package com.example.androidprotodatastore.data




import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.example.androidprotodatastore.Contact
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

class MySerializer : Serializer<Contact> {

    override fun readFrom(input: InputStream): Contact {
        try {
            return Contact.parseFrom(input)
        }
        catch (e : InvalidProtocolBufferException){
            throw CorruptionException("cannot read",e)
        }
    }

    override fun writeTo(t: Contact, output: OutputStream) {
       t.writeTo(output)
    }

    override val defaultValue: Contact
        get() = Contact.getDefaultInstance()

}