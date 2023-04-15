function TLocalStorage(item) {
  const self = this;

  self.Save = function (data) {
    const json = JSON.stringify(data);
    window.localStorage.setItem(item, json);
  }

  self.Load = function () {
    const json = window.localStorage.getItem(item);
    return JSON.parse(json);
  }
}

function THashStorage() {
  const self = this;
  const data = {};
  const local = new TLocalStorage('data');

  self.Save = function () {
    local.Save(data);
  }

  self.Load = function () {
    const loadedData = local.Load();
    if (!loadedData) return;
    for (key in loadedData) {
      this.AddValue(key, loadedData[key]);
    }
  }

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

    for (const key in data) {
      str += `${key} - ${this.GetValue(key)}\n`;
    }

    return str;
  }

  self.Reset = function () {
    for (const key in data) {
      this.DeleteValue(key);
    }
  }
}

const Storage = new THashStorage();
Storage.Load();

const actionCancelledStr = 'Action cancelled';

function PromptSave() {
  const save = confirm('Do you want to save changes?');

  if (!save) {
    return;
  }

  Storage.Save();
}

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

  Storage.AddValue(login, name);
  PromptSave()
}

function PromptClear() {
  const clear = confirm('Are you sure you want to clear current list of visitors?');

  if (!clear) {
    alert(actionCancelledStr);
    return;
  }

  Storage.Reset();
  PromptSave();
}

function PromptDeleteVisitor() {
  const login = prompt('Login of the deleted visitor?');

  if (!login) {
    alert(actionCancelledStr);
    return;
  }

  if (!Storage.GetValue(login)) {
    alert('Error: there is no such visitor');
    return;
  }

  const confirmed = confirm('Are you sure you want to delete this visitor?');

  if (!confirmed) {
    alert(actionCancelledStr);
    return;
  }

  Storage.DeleteValue(login);
  PromptSave();
}

function AlertVisitorName() {
  const login = prompt('Login of the visitor?');

  if (!login) {
    alert(actionCancelledStr);
    return;
  }

  if (!Storage.GetValue(login)) {
    alert('Error: there is no such visitor');
    return;
  }

  alert(Storage.GetValue(login));
}

function LogAllVisitors() {
  const log = Storage.ListValues();

  if (log.length === 0) {
    console.log('No registered visitors yet');
    return;
  }

  console.log(log);
}