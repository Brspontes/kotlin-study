package br.com.alura.alugames.servicos

import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class Api {
    fun getData(id: String): String {
        val endereco = "https://www.cheapshark.com/api/1.0/games?id=$id"

        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(endereco))
            .build()


        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        val json = response.body()

       return json
    }
}