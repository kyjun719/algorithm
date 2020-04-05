https://dev-kimse9450.tistory.com/17

main 함수에서 다음과 같은 코드가 있음

```java
BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
...
scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
```

###### 환경변수 "OUTPUT_PATH"를 가져와서 파일에 씀
- 콘솔 출력시 다음과 같이 변경
- BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

###### (\r\n|[\n\r\u2028\u2029\u0085])? 의미
기호 | 의미
---|------
/r/n	| Windows line ending
/n		| UNIX line ending
/r		| Macintosh (pre-OSX) line ending
\u2028 	| LINE SEPARATOR
\u2029	| PARAGRAPH SEPARATOR
\u0085	| NEXT LINE(NEL)
