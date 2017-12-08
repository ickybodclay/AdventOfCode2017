import java.io.BufferedReader
import java.io.FileReader

class Day6 {
    private fun readInput() : List<Int> {
        val reader = BufferedReader(FileReader("day6_input.txt"))
        val memBanksArray = mutableListOf<Int>()
        reader.readLine().split("\t").map {
            memBanksArray.add(it.toInt())
        }
        return memBanksArray
    }

    private fun redistribute(memBank: List<Int>) : List<Int> {
        val newMemBank = mutableListOf<Int>()
        newMemBank.addAll(memBank)

        var max = -1
        var maxIndex = 0
        for (i in memBank.indices) {
            if (memBank[i] > max) {
                max = memBank[i]
                maxIndex = i
            }
        }

        var j = maxIndex
        var blocksToRedistribute = memBank[j]
        for (i in memBank.indices) {
            if (i==0) {
                newMemBank[j] = 0
            }
            else if (blocksToRedistribute > 0){
                newMemBank[j] = memBank[j] + 1
                blocksToRedistribute--
            }
            else {
                break
            }

            j++
            if (j >= memBank.size) {
                j = 0
            }
        }

        return newMemBank
    }

    fun printPart1Solution() {
        println("[Part 1]")
        val memBanksHistory = mutableListOf<List<Int>>()
        var memBanks = readInput()
        memBanksHistory.add(memBanks)
        println("mem banks = " + memBanks)

        var cycle = 1
        while (true) {
            val newMemBank = redistribute(memBanks)

            if (memBanksHistory.contains(newMemBank)) {
                println ("cycles to see duplicate = $cycle")
                break
            }

            memBanksHistory.add(newMemBank)
            memBanks = newMemBank
            cycle++
        }
    }

    fun printPart2Solution() {
        println("[Part 2]")
    }
}

fun main(args: Array<String>) {
    Day6().printPart1Solution()
    //Day6().printPart2Solution()
}