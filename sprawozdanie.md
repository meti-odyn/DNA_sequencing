# Sprawozdanie z działania algorytmu heurystycznego dla problemu sekwencjonowania łańcuchów DNA z błędami negatywnymi i pozytywnymi

## Autorzy
Stanisław Puzio 151886

Jakub Wolniak 151797

## Wprowadzenie
Celem niniejszego sprawozdania jest przedstawienie wyników testów algorytmu heurystycznego, który został zaprojektowany do sekwencjonowania łańcuchów DNA z błędami negatywnymi i pozytywnymi. Algorytm został przetestowany na różnych instancjach problemu, a wyniki obejmują czas obliczeń oraz jakość wygenerowanego rozwiązania dla każdej instancji. Dodatkowo, dokonano porównania działania heurystyki na zbiorach testowych różniących się rodzajem zawartych błędów.

## Algorytm
Algorytm napisany w języku Kotlin ma na celu rozwiązanie problemu sekwencjonowania łańcuchów DNA z błędami negatywnymi i pozytywnymi. Algorytm wykorzystuje heurystyczne podejście do rekonstrukcji oryginalnej sekwencji DNA na podstawie podanych podsekwencji. Główne elementy algorytmu obejmują tworzenie listy podsekwencji, heurystyczne poszukiwanie sekwencji oraz przetwarzanie plików zawierających instancje problemu.

Zmiany w oryginalnym algorytmie:
 - gdy jest już sekwencja o długości większej lub równej n to przestajemy dalej łączyć sekwencje i za ostateczny wynik przyjmujemy pierwsze n aminokwasów tej sekwencji 

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
| 509 | 509    | 78.001       | 72.40      |
| 509 | 509    | 22.582       | 53.60      |
| 209 | 209    | 6.591        | 71.00      |
| 209 | 209    | 4.287        | 49.50      |
| 309 | 309    | 8.261        | 52.67      |
| 309 | 309    | 14.323       | 73.33      |
| 209 | 209    | 12.785       | 73.00      |
| 209 | 209    | 6.006        | 56.00      |
| 509 | 509    | 33.333       | 73.60      |
| 509 | 509    | 25.812       | 55.20      |
| 309 | 309    | 11.102       | 51.00      |
| 309 | 309    | 16.552       | 74.00      |
| 409 | 409    | 17.044       | 56.50      |
| 409 | 409    | 27.943       | 73.75      |
| 309 | 309    | 9.992        | 53.33      |
| 309 | 309    | 15.945       | 75.33      |
| 409 | 409    | 18.477       | 55.00      |
| 409 | 409    | 27.220       | 76.00      |
| 409 | 409    | 13.301       | 55.25      |
| 409 | 409    | 20.599       | 74.75      |
| 209 | 209    | 5.167        | 74.50      |
| 209 | 209    | 3.252        | 51.00      |

#### Instancje z błędami negatywnymi wynikającymi z powtórzeń

| n   | length | time (ms)    | jakość (%) |
|-----|--------|--------------|------------|
| 509 | 509    | 49.943       | 97.40      |
| 509 | 509    | 21.899       | 97.20      |
| 509 | 509    | 16.653       | 95.80      |
| 509 | 509    | 23.277       | 92.00      |
| 509 | 509    | 26.697       | 95.60      |

#### Pozytywne losowe

| n   | length | time (ms)    | jakość (%) |
|-----|--------|--------------|------------|
| 509 | 509    | 43.775       | 100.00     |
| 209 | 209    | 8.415        | 88.00      |
| 309 | 309    | 16.038       | 100.00     |
| 509 | 509    | 39.278       | 100.00     |
| 209 | 209    | 6.019        | 100.00     |
| 509 | 509    | 35.956       | 100.00     |
| 309 | 309    | 16.945       | 72.33      |
| 409 | 409    | 35.034       | 79.50      |
| 309 | 309    | 18.729       | 66.67      |
| 409 | 409    | 33.948       | 88.00      |
| 409 | 409    | 25.927       | 100.00     |
| 209 | 209    | 5.632        | 100.00     |

#### Pozytywne przekłamania na końcach oligonukleotydów

| n   | length | time (ms)    | jakość (%) |
|-----|--------|--------------|------------|
| 509 | 509    | 19.833       | 100.00     |
| 209 | 209    | 3.478        | 88.50      |
| 309 | 309    | 8.038        | 100.00     |
| 509 | 509    | 22.948       | 100.00     |
| 209 | 209    | 4.963        | 96.50      |
| 509 | 509    | 23.565       | 100.00     |
| 309 | 309    | 11.738       | 72.33      |
| 409 | 409    | 17.073       | 83.00      |
| 309 | 309    | 7.703        | 94.00      |
| 409 | 409    | 13.819       | 88.75      |
| 409 | 409    | 15.890       | 100.00     |
| 209 | 209    | 4.776        | 100.00     |

### Analiza

1. **Instancje z błędami negatywnymi losowymi:**
   - Jakość rozwiązania waha się od 49.50% do 76.00%.
   - Czas obliczeń wynosi od 3.252 ms do 78.001 ms.
   - Widać, że algorytm radzi sobie lepiej dla mniejszych wartości n, jednak dla większych n jakość rozwiązania bywa znacznie niższa.

2. **Instancje z błędami negatywnymi wynikającymi z powtórzeń:**
   - Jakość rozwiązania waha się od 92.00% do 97.40%.
   - Czas obliczeń wynosi od 16.653 ms do 49.943 ms.
   - Powtórzenia wpływają na jakość, jednak algorytm jest w stanie uzyskać wysokie wyniki jakości w niektórych przypadkach.

3. **Pozytywne losowe:**
   - Jakość rozwiązania wynosi od 66.67% do 100.00%.
   - Czas obliczeń wynosi od 5.632 ms do 43.775 ms.
   - Algorytm dobrze poradził sobie z pozytywnymi losowymi błędami, często uzyskując najwyższą jakość.

4. **

Pozytywne przekłamania na końcach oligonukleotydów:**
   - Jakość rozwiązania waha się od 72.33% do 100.00%.
   - Czas obliczeń wynosi od 3.478 ms do 23.565 ms.
   - Algorytm radzi sobie bardzo dobrze z tym rodzajem błędów, często uzyskując maksymalną jakość rozwiązania.
