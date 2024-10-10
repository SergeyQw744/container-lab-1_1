package org.example;

import java.util.Arrays;

/**
 * Класс контейнер, первоначально содержащий массив из 10 элементов.
 * При создании size = 10 и все элементы в data - null-значения.
 * При добавлении нового объекта в контейнер, он записывается в пустую
 * ячейку массива, если таких ячеек уже нет, будет сформирован новый массив,
 * с длинной на 1 больше, чем размер предыдущего массива data
 * и элемент будет занесен сюда. Добавление происходит в конец, в начало и в середину.
 * При добавлении по индексу (в начало или середину) происходит сдвиг данных.
 * При количестве элементов больше 10 добавление приводит к увеличению размера массива на 1.
 * При удалении объекта из контейнера происходит смещение элементов, расположенных
 * справа от удаляемого (если они есть), при этом если длинна массива больше 10,
 * удаление сократит размер массива на 1. Также не меняется порядок элементов.
 * Если же длинна data не более 10, размер массива не изменится (его размер всегда
 * будет хотя бы 10), а пустые значения массива содержат null.
 */
public class Container<T> {
    private T[] data;
    private int size;

    public Container() {
        data = (T[]) new Object[10];
        size = 10;
    }

    /**
     * Добавление
     * На вход метод принимает объект, который надо добавить в контейнер.
     * Вычисляется номер свободной позиции (метод getFreePosition()) и если он равен size,
     * требуется увеличить массив на одну ячейку (при этом перезаписав все данные) (метод resize())
     * и уже добавить новый объект в конец массива. Если номер свободной позиции меньше size,
     * новый объект добавляется в ячейку с этим номером.
     * @param object объект, который требуется поместить в контейнер
     */
    public void add(T object) {
        int indexFreePosition = getFreePosition();
        if (indexFreePosition == size) {
            resize();
            data[size - 1] = object;
        } else {
            data[indexFreePosition] = object;
        }
    }

    /**
     * Добавление по индексу
     * На вход метод принимает индекс, по которому происходит вставка элемента и сохраняемый объект.
     * Перед добавлением происходит проверка индекса на допустимость (метод
     * indexValidation(int index)). При этом возвращается сообщение, содержащее описание ошибки
     * (недопустимое значение индекса). После осуществляется сдвиг данных вправо от ячейки, в которою
     * требуется записать новый объект в массиве data (метод shiftDataInRight(int index)).
     * Если введен недопустимый индекс, кидается исключение с описанием ошибки.
     * @param index индекс(номер ячейки), в которую необходимо разместить объект
     * @param object объект, который требуется поместить в контейнер
     * @throws IncorrectIndexException исключение, которое будет выброшено при неверно введенном индексе
     * @see #indexValidation(int index)
     */
    public void addByIndex(int index, T object){
        String message = indexValidation(index);
        if (message.isBlank()){
            shiftDataInRight(index);
            data[index] = object;
        } else {
            throw new IncorrectIndexException(message);
        }
    }

    /**
     * Извлечение
     * На вход метод принимает индекс, по которому надо извлечь объект.
     * Перед возвращением элемента, проверяется допустимость вводимого индекса (метод indexValidation(int index)),
     * При этом возвращается сообщение, содержащее описание ошибки (недопустимое значение индекса).
     * Если сообщение пустое, метод возвращает объект по индексу, иначе кидается исключение с описанием ошибки.
     * @param index индекс(номер ячейки), ао которому осуществляется поиск
     * @throws IncorrectIndexException исключение, которое будет выброшено при неверно введенном индексе
     * @see #indexValidation(int index)
     */
    public T find(int index) {
        String message = indexValidation(index);
        if (message.isBlank()) {
            return data[index];
        } else {
            throw new IncorrectIndexException(message);
        }
    }

    /**
     * Удаление
     * На вход метод принимает индекс, по которому надо удалить объект.
     * Перед удалением элемента, проверяется допустимость вводимого индекса (метод indexValidation(int index)).
     * Удаление заключается в правильном смещении данных в массиве data (метод shiftData(T[] dataOriginal, int index).
     * Метод возвращает значение удаленного объекта. Если введен недопустимый индекс, кидается исключение с описанием ошибки.
     * @param index индекс(номер ячейки), по которому осуществляется удаление
     * @throws IncorrectIndexException исключение, которое будет выброшено при неверно введенном индексе
     * @see #indexValidation(int index)
     * @see #shiftData(T[] dataOriginal, int index)
     */
    public T delete(int index) {
        String message = indexValidation(index);
        if (message.isBlank()) {
            T removedElement = data[index];
            shiftData(data, index);
            return removedElement;
        } else {
            throw new IncorrectIndexException(message);
        }
    }

    /**
     * Метод, возвращающий номер свободной позиции
     * Если в массиве нет пустых ячеек, метод возвращает значение size
     */
    private int getFreePosition() {
        int i = 0;
        while (i < size && data[i] != null) {
            i++;
        }
        return i;
    }

    /**
     * Метод, увеличивающий data на одну ячейку (создание нового массива с размером size + 1)
     * При этом происходит перезапись элементов в новый массив из старого (метод copyData(T[] originalData, T[] copy))
     * и устанавливается новое значение size
     * @see #copyData(T[] originalData, T[] copy)
     */
    private void resize() {
        T[] resizeEmptyData = (T[]) new Object[size + 1];
        data = copyData(data, resizeEmptyData);
        size = size + 1;
    }

    private T[] copyData(T[] originalData, T[] copy) {
        for (int i = 0; i < originalData.length; i++) {
            copy[i] = originalData[i];
        }
        return copy;
    }

    private String indexValidation(int index) {
        if (index >= 0 && index < size) {
            return "";
        } else {
            return "The index must be between 0 and " + (size - 1);
        }
    }

    /**
     * Смещение данных (используется при удалении)
     * Метод принимает на вход параметры, такие как массив оригинальных данных (копия data) и
     * индекс, по которому происходит удаление объекта, по сути здесь он нужен для смещения
     * данных влево. Сначала происходит корректировка размера массива, после чего этот массив создается.
     * Далее требуется правильно перезаписать данные. В целом элементы справа от позиции входного
     * индекса просто переписываются, а после этого индекса переписываются со сдвигом влево на 1.
     * При этом учитываются случаи, когда удаляемый элемента находится в первой или последней ячейке
     * массива. В конце происходит редактирование полей data и size.
     * @param dataOriginal массив данных, из которого перезаписываются данные
     * @param index номер ячейки, начиная с которой происходит сдвиг элементов
     */
    private void shiftData(T[] dataOriginal, int index) {
        int newSize;
        if (data.length > 10){
            newSize = data.length - 1;
        } else {
            newSize = 10;
        }
        T[] copy = (T[]) new Object[newSize];
        if (index > 0) {
            for (int i = 0; i < index; i++) {
                copy[i] = dataOriginal[i];
            }
            if (index < data.length - 1) {
                for (int i = index + 1; i < data.length; i++) {
                    copy[i - 1] = dataOriginal[i];
                }
            }
        } else {
            for (int i = index + 1; i < data.length; i++) {
                copy[i - 1] = dataOriginal[i];
            }
        }
        if (size > 10){
            size = size - 1;
        }
        data = copy;
    }

    /**
     * Смещение данных вправо от заданной позиции.
     * На вход элемент принимает значение индекса ячейки, начиная с которой происходит
     * смещение данных вправо. Сначала происходит проверка наличия свободных ячеек в data
     * (метод areFreePositions()) и если есть свободное место, все данные смещаются с конца
     * массива вправо, в противном случае создается новый массив с размером, большим на 1,
     * в который перезаписываются значения из старого (метод copyData(T[] originalData, T[] copy)),
     * редактируется после size, а только потом происходит смещение данных.
     * @param index номер ячейки, начиная с которой происходит сдвиг элементов
     * @see #areFreePositions()
     * @see #copyData(T[] originalData, T[] copy)
     */
    private void shiftDataInRight(int index){
        if (areFreePositions()){
            for (int i = size - 1; i > index; i--){
                data[i] = data[i - 1];
            }
        } else {
            data = copyData(data, (T[]) new Object[size + 1]);
            size = size + 1;
            for (int i = size - 1; i > index; i--){
                data[i] = data[i - 1];
            }
        }
    }

    /**
     * Проверка наличия пустых ячеек в массиве data
     * высчитывается номер пустой ячейки и если он находится в пределах нумерации элементов массива,
     * возвращается значение true, иначе false.
     */
    private boolean areFreePositions(){
        int i = 0;
        while (i < size && data[i] != null){
            i++;
        }
        if (i < size - 1){
            return true;
        }
        return false;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Container<?> container = (Container<?>) o;
        return size == container.size && Arrays.equals(data, container.data);
    }

    public T[] getData() {
        return data;
    }

    public int getSize() {
        return size;
    }
}
