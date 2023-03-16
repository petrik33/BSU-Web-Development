﻿**Лабораторная работа №5**

**Динамические формы в JavaScript. Валидация.**

1\. В проект лабораторной работы №2 добавить html-страницу (переход на нее должен выполняться по ссылке из меню главной страницы).

На страницу добавить HTML форму, на которой будут располагаются другие элементы управления – текстовые поля, кнопки, метки. Кроме тега **form** других HTML-тегов на форме быть не должно. Вся информация об элементах формы (типы полей, подписи к полям и т.д.) должна быть закодирована в виде одного массива.

Формат массива может быть следующим

var FormA= 

[ 

`  `{ label: 'Разработчики:', elemtype:'text1line', name:'developers', width:200 }, 

`  `{ label: 'Разрешить отзывы:', elemtype:'check', name:'enablecomments' }, 

`  `…..

{ label: 'Отослать:', elemtype:'button', value:'Send' }

];

Написать **JavaScript** функцию, которая будет получать такой массив и имя формы и динамически (методами DOM) создавать поля в форме.


2\. Написать **JavaScript** функцию, которая делает **валидацию** вводимых в форму значений; правила валидации продумать самостоятельно.

При изменении значения поля формы — валидировать только данное поле и в случае ошибки сообщение об ошибке сразу отобразить рядом с полем (сообщения об ошибках возле остальных полей не должны стираться).

При попытке отправки формы— валидировать сразу все поля и отобразить сразу все сообщения об ошибках рядом с ошибочно заполненными полями.




||Срок выдачи задания:|Срок сдачи задания:|
| - | - | - |
|12 группа|06\.03.23|<p>з.1 - 13.03.23</p><p>з.2 - 20.03.23</p>|
|13 группа|06\.03.23|<p>з.1 - 15.03.23</p><p>з.2 - 22.03.23</p>|
|14 группа|06\.03.23|<p>з.1 - 17.03.23</p><p>з.2 - 24.03.23</p>|

Максимальное количество баллов за работу – 30. 

Номер окончательной версии:

1 версия – коэффициент 1, 2 версия – коэффициент 0,6, 3 версия – коэффициент 0,4.

Дата сдачи

До указанной даты – коэффициент 1, до 25.03 – коэффициент 0,6, позже – коэффициент 0,4.

1\. Ботанический сад. 

2\. Периодические издания. 

3\. Сборник кулинарных рецептов. 

4\. Звукозапись.

5\. Ювелирный магазин. 

6\. Магазин автозапчастей. 

7\. Транспортная компания. 

8\. Авиакомпания. 

9\.Таксопарк. 

10\. Салон мобильной связи. 

11\. Кафе. 

12\. Игровая комната в торговом центре. 

13\. Банковские счета.  

14\. Туристическое агентство. 

15\. Магазин холодного оружия. 

16\. Танцевальный коллектив. 

17\. Аптека. 

18\. Налоговая инспекция. 

19\. Магазин детских игрушек. 

20\. Отдел кадров. 

21\. Рекламное агентство. 

22\. Страховая организация. 

23\. Отдел информационных технологий предприятия.

24\. Зоопарк. 


