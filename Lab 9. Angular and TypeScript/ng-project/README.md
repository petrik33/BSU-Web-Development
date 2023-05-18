# Лабораторная работа №9

## TypeScript, Angular

### Specification 12. Игровая комната в торговом центре

Создать модуль (module) с названием `ToyRoom`. В этом модуле разместить компоненты (component) `ToyCenter`, `ToyList`, `ToyDetails`. Добавить навигацию: при открытии проекта на место элемента `<router-outlet>` отображается компонент `ToyCenter`, для которого дочерним подгружается компонент ToyList. При нажатии на один из элементов списка ToyList подгружается соответствующий компонент `ToyDetails`. 
Список игрушек хранить в приложении в файле mock-toy-list.ts в виде массива: `Toys [] = [ { id: 1, description: 'ball', age: '3' }, … ]`

На основе предметной области [лабораторной работы №1](https://github.com/petrik33/BSU-Web-Development/tree/main/Lab%201.%20HTML%2C%20CSS) (Specification)
спроектировать Angular приложение. 
Сгенерировать шаблон-заготовку приложения можно при помощи команды angular cli:

```bash
ng new <имя_приложения>
```

Корневой компонент приложения - app.component (генерируется автоматически при создании приложения и размещается в папке src\app). В app.component.html (html шаблоне корневого компонента) разместить bootstrap компонент jumbotron, с описанием функционала приложения (необходимо также добавить bootstrap зависимость в файл package.json).
В папке проекта src\app сгенерировать модуль с названием согласно варианта задания. Это можно сделать командой angular cli:

```bash
ng generate module <имя_модуля> --module=app
```

Перейти в папку src\app\имя_модуля и сгенерировать компоненты согласно варианта задания:

```bash
ng generate component <имя_компонента>
```

Для взаимодействия с данными в папке src\app\имя_модуля создать папку services и в ней сгенерировать сервис при помощи команды:

```bash
ng generate service <имя_модуля>
```

Для осуществления маршрутизации в приложении в корень проекта (на одном уровне с app.component) добавить модуль AppRoutingModule. Это можно сделать командой:

```bash
ng generate module app-routing  --flat --module=app
```

В app.component.html разместить директиву `<router-outlet> </router-outlet>` (На место элемента `<router-outlet>` будет рендериться компонент, выбранный для обработки запроса.).

Для обработки дочерних маршрутов в папке src\app\имя_модуля добавить модуль имя_модуляRoutingModule командой:

```bash
ng generate module имя_модуля-routing  --flat --module= имя_модуля
```

### Report

![Toy Center](../Screenshot%20from%202023-05-18%2023-53-58.png)

![Toy Details](../Screenshot%20from%202023-05-18%2023-54-37.png)
