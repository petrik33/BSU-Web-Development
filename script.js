const data = {};

function AddValue(key, value) {
  data[key] = value;
}

function DeleteValue(key) {
  data[key] = undefined;
}

function GetValueInfo(key) {
  return data[key];
}

function ListValues() {
  const str = '';
  for (let key in data) {
    str += `${key} - ${data[key]}\n`;
  }
}