package by.it.group551003.tarasau.lesson06;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
Задача на программирование: наибольшая невозростающая подпоследовательность

Дано:
    целое число 1<=n<=1E5 ( ОБРАТИТЕ ВНИМАНИЕ НА РАЗМЕРНОСТЬ! )
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] не больше любого предыдущего
    т.е. для всех 1<=j<k, A[i[j]]>=A[i[j+1]].

    В первой строке выведите её длину k,
    во второй - её индексы i[1]<i[2]<…<i[k]
    соблюдая A[i[1]]>=A[i[2]]>= ... >=A[i[n]].

    (индекс начинается с 1)

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    5
    5 3 4 4 2

    Sample Output:
    4
    1 3 4 5
*/


public class C_LongNotUpSubSeq {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = B_LongDivComSubSeq.class.getResourceAsStream("dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.print(result);
    }

    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //общая длина последовательности
        int n = scanner.nextInt();
        int[] m = new int[n];
        //читаем всю последовательность
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи методами динамического программирования (!!!)
        int result = 1;

        int[] tails = new int[n];
        int[] prevs = new int[n];
        int length = 1;
        int seqEnd = 0;

        Arrays.fill(tails, 1);
        Arrays.fill(prevs, -1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (m[j] >= m[i] && tails[j] + 1 > tails[i]) {
                    tails[i] = tails[j] + 1;  // запоминаем сколько "вариантов" привело сюда
                    prevs[i] = j;  // запоминаем позицию предыдущего элемента
                }
            }

            if (tails[i] > length) {  // увеличиваем только если длина вышла больше чем была
                length = tails[i];
                seqEnd = i;  // запоминаем текущий последний элем. последовательности для развёртки
            }
        }

        result = length;
        System.out.println(result);

        List<Integer> seq = new ArrayList<>();
        int current = seqEnd;
        while (current != -1) {
            seq.add(current+1);
            current = prevs[current];  // после записи current, в массиве prevs по индексу current содержится следующий
        }

        seq = seq.reversed(); // они записывались в обратном порядке, меняем порядок на прямой

        for (int i = 0; i < seq.size(); i++) { // вывод
            System.out.print(seq.get(i) + " ");
        }
        System.out.println();

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

}