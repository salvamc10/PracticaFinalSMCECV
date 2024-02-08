package com.example.practicafinalsmcecv

class PartidoRepository {
    data class Partido(
        val id: Int,
        val nombre: String,
        val fecha: String,
        val hora: String,
        val estadio: String,
        val competicion: String
    )
}
