import './styles/styles.css';
import './styles/variables.css';

import { concatMap, fromEvent } from 'rxjs';
import { switchMap, map } from 'rxjs';
import { ajax } from 'rxjs/ajax';

const showDataButton = document.getElementById('show-data');
const deleteDataButton = document.getElementById('delete-data');
const dataContainer = document.getElementById('data-container')

const loadVisitor = (login, name) => {
  const row = document.createElement('tr');
  const loginCell = document.createElement('td');
  loginCell.textContent = login;
  const nameCell = document.createElement('td');
  nameCell.textContent = name;
  row.appendChild(loginCell);
  row.appendChild(nameCell);
  return row;
}

const data$ = from(ajax.getJSON('/data'));

const showData$ = fromEvent(showDataButton, 'click').pipe(
  switchMap(() => data$),
  concatMap(data => from(data)),
  map(({ login, name }) => loadVisitor(login, name))
);

showData$.subscribe(row => {
  dataContainer.appendChild(row);
});

const deleteLastDataRow = () => {
  const rows = dataContainer.querySelectorAll('tr');
  if (rows.length > 0) {
    const lastRow = rows[rows.length - 1];
    lastRow.parentNode.removeChild(lastRow);
  }
};

const deleteData$ = fromEvent(deleteDataButton, 'click');
deleteData$.subscribe(deleteLastDataRow);
