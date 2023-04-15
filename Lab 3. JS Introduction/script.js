const data = {};

function AddValue(key, value) {
  data[key] = value;
}

function DeleteValue(key) {
  delete data[key];
}

function GetValueInfo(key) {
  return data[key];
}

function ListValues() {
  let str = '';

  for (let key in data) {
    str += `${key} - ${data[key]}\n`;
  }

  return str;
}

const actionCancelledStr = 'Action cancelled';

function PromptAddVisitor() {
  const name = prompt('The name of the visitor?');

  if (!name) {
    alert(actionCancelledStr);
    return;
  }

  const login = prompt('Login?');

  if (!login) {
    alert(actionCancelledStr);
    return;
  }

  if (data[login]) {
    const override = confirm('This login already exists. Would you like to override the existing name?');

    if (!override) {
      alert(actionCancelledStr);
      return;
    }
  }

  alert('Sucessful!');

  AddValue(login, name);
}

function PromptDeleteVisitor() {
  const login = prompt('Login of the deleted visitor?');

  if (!login) {
    alert(actionCancelledStr);
    return;
  }

  if (data[login]) {
    const confirmed = confirm('Are you sure you want to delete this visitor?');

    if (!confirmed) {
      alert(actionCancelledStr);
      return;
    }

    DeleteValue(login);
    alert('Sucessful!');
    return;
  }

  alert('Error: there is no such visitor');
}

function AlertVisitorName() {
  const login = prompt('Login of the visitor?');

  if (!login) {
    alert(actionCancelledStr);
    return;
  }

  if (data[login]) {
    alert(GetValueInfo(login));
    return;
  }

  alert('Error: there is no such visitor');
}

function LogAllVisitors() {
  const log = ListValues();

  if (log.length === 0) {
    console.log('No registered visitors yet');
    return;
  }

  console.log(log);
}