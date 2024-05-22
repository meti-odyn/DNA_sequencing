# Sprawozdanie z działania algorytmu heurystycznego dla problemu sekwencjonowania łańcuchów DNA z błędami negatywnymi i pozytywnymi

## Autorzy
Stanisław Puzio <WPISAĆ INDEKS>
Jakub Wolniak 151797

## Wprowadzenie
Celem niniejszego sprawozdania jest przedstawienie wyników testów algorytmu heurystycznego, który został zaprojektowany do sekwencjonowania łańcuchów DNA z błędami negatywnymi i pozytywnymi. Algorytm został przetestowany na różnych instancjach problemu, a wyniki obejmują czas obliczeń oraz jakość wygenerowanego rozwiązania dla każdej instancji. Dodatkowo, dokonano porównania działania heurystyki na zbiorach testowych różniących się rodzajem zawartych błędów.

## Algorytm
Algorytm napisany w języku Kotlin ma na celu rozwiązanie problemu sekwencjonowania łańcuchów DNA z błędami negatywnymi i pozytywnymi. Algorytm wykorzystuje heurystyczne podejście do rekonstrukcji oryginalnej sekwencji DNA na podstawie podanych podsekwencji. Główne elementy algorytmu obejmują tworzenie listy podsekwencji, heurystyczne poszukiwanie sekwencji oraz przetwarzanie plików zawierających instancje problemu.

## Dane testowe
Zbiory testowe różniły się liczbą podzbiorów. Testy przeprowadzono na instancjach z różnymi rodzajami błędów:
- **Błędy negatywne losowe**: losowe braki w danych.
- **Błędy negatywne wynikające z powtórzeń**: duplikaty elementów w podzbiorach.
- **Błędy pozytywne losowe**: nadmiarowe elementy w podzbiorach.
- **Błędy pozytywne przekłamania na końcach**: błędne elementy na końcach podzbiorów.

## Analiza wyników działania algorytmu

### Wyniki testów

#### Instancje z błędami negatywnymi losowymi

| n   | length | time (ms)    | jakość (%) |
|-----|--------|--------------|------------|
| 509 | 366    | 71.356       | 71.90      |
| 509 | 451    | 21.281       | 88.61      |
| 209 | 209    | 4.363        | 100.00     |
| 209 | 137    | 4.250        | 65.55      |
| 309 | 305    | 6.771        | 98.71      |
| 309 | 178    | 9.089        | 57.61      |
| 209 | 209    | 5.548        | 100.00     |
| 209 | 209    | 5.835        | 100.00     |
| 509 | 309    | 39.659       | 60.71      |
| 509 | 509    | 23.517       | 100.00     |
| 309 | 182    | 10.367       | 58.90      |
| 309 | 308    | 15.471       | 99.68      |
| 409 | 385    | 16.475       | 94.14      |
| 409 | 409    | 22.116       | 100.00     |
| 309 | 205    | 8.172        | 66.34      |
| 309 | 308    | 13.267       | 99.68      |
| 409 | 409    | 14.492       | 100.00     |
| 409 | 333    | 28.354       | 81.42      |
| 409 | 409    | 13.142       | 100.00     |
| 409 | 409    | 20.159       | 100.00     |
| 209 | 209    | 6.332        | 100.00     |
| 209 | 189    | 3.653        | 90.43      |

#### Instancje z błędami negatywnymi wynikającymi z powtórzeń

| n   | length | time (ms)    | jakość (%) |
|-----|--------|--------------|------------|
| 509 | 509    | 38.198       | 100.00     |
| 509 | 509    | 30.126       | 100.00     |
| 509 | 485    | 31.031       | 95.29      |
| 509 | 407    | 25.178       | 79.96      |
| 509 | 312    | 27.094       | 61.30      |

#### Pozytywne losowe

| n   | length | time (ms)    | jakość (%) |
|-----|--------|--------------|------------|
| 509 | 509    | 58.630       | 100.00     |
| 209 | 209    | 9.067        | 100.00     |
| 309 | 309    | 22.062       | 100.00     |
| 509 | 509    | 61.392       | 100.00     |
| 209 | 209    | 10.844       | 100.00     |
| 509 | 509    | 55.132       | 100.00     |
| 309 | 309    | 20.622       | 100.00     |
| 409 | 409    | 34.109       | 100.00     |
| 309 | 309    | 18.952       | 100.00     |
| 409 | 409    | 33.086       | 100.00     |
| 409 | 409    | 31.776       | 100.00     |
| 209 | 209    | 10.803       | 100.00     |

#### Pozytywne przekłamania na końcach oligonukleotydów

| n   | length | time (ms)    | jakość (%) |
|-----|--------|--------------|------------|
| 509 | 509    | 29.213       | 100.00     |
| 209 | 209    | 5.404        | 100.00     |
| 309 | 309    | 8.172        | 100.00     |
| 509 | 509    | 25.024       | 100.00     |
| 209 | 209    | 3.868        | 100.00     |
| 509 | 509    | 25.926       | 100.00     |
| 309 | 259    | 8.720        | 83.82      |
| 409 | 409    | 13.795       | 100.00     |
| 309 | 309    | 9.995        | 100.00     |
| 409 | 409    | 18.365       | 100.00     |
| 409 | 409    | 15.054       | 100.00     |
| 209 | 209    | 3.642        | 100.00     |

### Analiza

1. **Instancje z błędami negatywnymi losowymi:**
   - Jakość rozwiązania waha się od 57.61% do 100.00%.
   - Czas obliczeń wynosi od 3.653 ms do 71.356 ms.
   - Widać, że algorytm radzi sobie lepiej dla mniejszych wartości n, jednak dla większych n jakość rozwiązania bywa znacznie niższa.

2. **Instancje z błędami negatywnymi wynikającymi z powtórzeń:**
   - Jakość rozwiązania waha się od 61.30% do 100.00%.
   - Czas obliczeń wynosi od 25.178 ms do 38.198 ms.
   - Powtórzenia wpływają na jakość, jednak algorytm jest w stanie uzyskać wysokie wyniki jakości w niektórych przypadkach.

3. **Pozytywne losowe:**
   - Jakość rozwiązania wynosi 100.00% we wszystkich przypadkach.
   - Czas obliczeń wynosi od 9.067 ms do 61.392 ms.
   - Algorytm dobrze poradził sobie z pozytywnymi losowymi błędami, zawsze uzyskując najwyższą jakość.

4. **Pozytywne przekłamania na końcach oligo:**
   - Jakość rozwiązania wynosi głównie 100.00%, z wyjątkiem jednego przypadku (83.82%).
   - Czas obliczeń wynosi od 3.642 ms do 29.213 ms.
   - Przekłamania na końcach oligo mają niewielki wpływ na jakość, algorytm nadal osiąga wysoką dokładność.

#### Wnioski

1. **Plusy:**
   - Dobrze radzi sobie z pozytywnymi błędami, osiągając najwyższą jakość w wielu przypadkach.
   - Jakość rozwiązania jest wysoka dla większości testowanych instancji.

2. **Minusy:**
   - Algorytm ma problemy z instancjami zawierającymi negatywne błędy losowe, gdzie jakość rozwiązania jest niższa.
   - Powtórzenia w danych negatywnie wpływają na jakość w niektórych przypadkach.
