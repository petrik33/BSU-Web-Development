# Лабораторная работа №5

## Динамические формы в JavaScript. Валидация

*Вариант 12: Игровая комната в торговом центре*

Deploy: https://petrik33.github.io/BSU-Web-Lab5/

### Task1

В проект [лабораторной работы №2](https://github.com/petrik33/BSU-Web-Lab2)
добавить html-страницу (переход на нее должен выполняться по ссылке из меню главной страницы).

На страницу добавить HTML форму, на которой будут располагаются другие элементы управления – текстовые поля, кнопки, метки. Кроме тега **form** других HTML-тегов на форме быть не должно. Вся информация об элементах формы (типы полей, подписи к полям и т.д.) должна быть закодирована в виде одного массива.

Формат массива может быть следующим

```js
var FormA= 

[ 

{ label: 'Разработчики:', elemtype:'text1line', name:'developers', width:200 }, 

{ label: 'Разрешить отзывы:', elemtype:'check', name:'enablecomments' }, 

…..

{ label: 'Отослать:', elemtype:'button', value:'Send' }

];
```

Написать **JavaScript** функцию, которая будет получать такой массив и имя формы и динамически (методами DOM) создавать поля в форме.

### Task2

Написать **JavaScript** функцию, которая делает **валидацию** вводимых в форму значений; правила валидации продумать самостоятельно.

При изменении значения поля формы — валидировать только данное поле и в случае ошибки сообщение об ошибке сразу отобразить рядом с полем (сообщения об ошибках возле остальных полей не должны стираться).

При попытке отправки формы — валидировать сразу все поля и отобразить сразу все сообщения об ошибках рядом с ошибочно заполненными полями

Тема: Игровая комната в торговом центре
