const menuData = [
  {
    name: 'Пункт 1', submenu: [
      {
        name: 'Пункт 1.1', submenu: [
          { name: 'Пункт 1.1.1', url: 'http://www.av.by' },
          { name: 'Пункт 1.1.2 длинный', url: 'http://www.av.by' }
        ]
      },
      { name: 'Пункт 1.2', url: 'http://www.av.by' },
      {
        name: 'Пункт 1.3 длинный', submenu: [
          { name: 'Пункт 1.3.1', url: 'http://www.av.by' },
          { name: 'Пункт 1.3.2', url: 'http://www.av.by' },
          { name: 'Пункт 1.3.3', url: 'http://www.av.by' },
          { name: 'Пункт 1.3.4 длинный', url: 'http://www.av.by' }
        ]
      }
    ]
  },
  { name: 'Пункт 2 длинный', url: 'http://www.av.by' },
  {
    name: 'Пункт 3', submenu: [
      { name: 'Пункт 3.1 длинный', url: 'http://www.av.by' },
      { name: 'Пункт 3.2', url: 'http://www.av.by' }
    ]
  }
];

const showMenu = (items, parent) => {
  const listElement = document.createElement('ul');
  items.forEach((item) => {
    const itemElement = makeMenuItem(item);
    listElement.appendChild(itemElement);
  });
  parent.appendChild(listElement);
}

const makeMenuItem = (item) => {
  const listItemElem = document.createElement('li');
  if (item.submenu) {
    listItemElem.append(item.name);
    listItemElem.onclick = (event) => {
      if (event.target !== event.currentTarget) return;
      if (hideMenu(listItemElem)) {
        listItemElem.classList.remove('opened');
        return;
      }
      showMenu(item.submenu, listItemElem);
      listItemElem.classList.add('opened');
    };
    listItemElem.classList.add('has-submenu');
  }
  if (item.url) {
    const anchor = makeAnchor(item.name, item.url);
    listItemElem.appendChild(anchor);
  }

  listItemElem.classList.add(['menu-item']);

  return listItemElem;
}

const makeAnchor = (name, url) => {
  const listItemAnchor = document.createElement('a');
  listItemAnchor.textContent = name;
  listItemAnchor.href = url;
  return listItemAnchor;
}

const hideMenu = (parent) => {
  const listElement = parent.querySelector('ul');
  if (listElement) {
    listElement.remove();
    return true;
  }
  return false;
}

const menuContainer = document.getElementById('tree-menu');

showMenu(menuData, menuContainer);