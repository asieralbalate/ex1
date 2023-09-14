package exemples

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

fun main(args: Array<String>) {
    var f = File.listRoots()[0]
    println(llistaDirectori(f))
    println("Introdueix un numero (-1 per acabar): ")
    val ent = BufferedReader(InputStreamReader(System.`in`)).readLine()
    val numero = ent.toInt()
    if (numero > -1 || numero <= f.length() + 1){
        f = File.listRoots()[numero - 1]
        if (f.isDirectory) {
            println(llistaDirectori(f))
        } else
            // Aqui quiero acabar si no es directorio, que se quede igual el programa
            System.exit(0)
    } else if (numero == -1) {
        System.exit(0)
    }
}

fun llistaDirectori(f: File) {
    val s = "Llista de fitxers i directoris del directori " + f.path
    var n = 1
    println(s)
    println("-".repeat(s.length))
    println("${0}.- " + f.parent)
    for (e in f.listFiles().sorted()) {
        if (e.isFile())
            println("${n}.- " + e.getName() + "\t " + e.length())
        if (e.isDirectory()) {
            println("${n}.- " + e.getName() + "\t <Directori>")
        }
        n++
    }
}


