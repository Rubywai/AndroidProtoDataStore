package com.example.androidprotodatastore.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.createDataStore
import com.example.androidprotodatastore.Contact
import kotlinx.coroutines.flow.catch
import java.io.IOException

class ProtoRepository(context : Context) {
    object Key{
        const val name = "myProto"
    }
    private val dataStore: DataStore<Contact> = context.createDataStore(
        Key.name,
        serializer =  MySerializer()
    )
    val read = dataStore.data
        .catch {exception ->
            if(exception is IOException){
                emit(Contact.getDefaultInstance())
            }
            else{
                throw  exception
            }
        }
    suspend fun write(name : String,age : Int,job : String) = dataStore.updateData { proto ->
            proto.toBuilder()
                .setName(name)
                .setAge(age)
                .setJob(job)
                .build()
    }
}