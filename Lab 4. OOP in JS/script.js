function THashStorage() {
  const self = this;
  const data = {};

  self.DeleteValue = function (key) {
    delete data[key];
  }

  self.AddValue = function (key, value) {
    data[key] = value;
  }

  self.GetValue = function (key) {
    return data[key];
  }

  self.GetKeys = function () {
    return Object.keys(data);
  }

  self.ListValues = function () {
    let str = '';

    for (let key in data) {
      str += `${key} - ${this.GetValue(key)}\n`;
    }

    return str;
  }

  self.Reset = function () {
    for (const key in data) {
      DeleteValue(key);
    }
  }
}

const Storage = new THashStorage();

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

  if (Storage.GetValue(login)) {
    const override = confirm('This login already exists. Would you like to override the existing name?');

    if (!override) {
      alert(actionCancelledStr);
      return;
    }
  }

  alert('Sucessful!');

  Storage.AddValue(login, name);
}

function PromptDeleteVisitor() {
  const login = prompt('Login of the deleted visitor?');

  if (!login) {
    alert(actionCancelledStr);
    return;
  }

  if (Storage.GetValue(login)) {
    const confirmed = confirm('Are you sure you want to delete this visitor?');

    if (!confirmed) {
      alert(actionCancelledStr);
      return;
    }

    Storage.DeleteValue(login);
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

  if (Storage.GetValue(login)) {
    alert(Storage.GetValue(login));
    return;
  }

  alert('Error: there is no such visitor');
}

function LogAllVisitors() {
  const log = Storage.ListValues();

  if (log.length === 0) {
    console.log('No registered visitors yet');
    return;
  }

  console.log(log);
}