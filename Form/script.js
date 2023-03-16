const formFields = [
  {
    label: 'Enter your name:',
    attributes: {
      type: 'text',
      name: 'name',
      required: true,
    },
    class: 'name',
  },
  {
    label: 'Enter your email:',
    attributes: {
      type: 'email',
      name: 'email',
      required: true
    },
    class: 'email',
  },
  {
    label: 'Enter rating:',
    attributes: {
      type: 'number',
      name: 'rating',
      required: true,
    },
    class: 'rating',
    min: 1,
    max: 10,
    validator: (value) => {
      if (!Number.isInteger(value)) {
        return errorType(name, 'integer');
      }
      if (value < min || value > max) {
        return errorRange(name, min, max);
      }
      return null;
    }
  },
  {
    label: 'Agree with terms and conditions',
    name: 'terms',
    class: '_terms',
    // validator: (value) => {
    //   if (value) {
    //     return null;
    //   }
    //   return 'you have to agree with terms and conditions';
    // }
  },
  {
    label: 'Yes',
    attributes: {
      type: 'radio',
      name: 'terms',
      id: 'yes',
      value: true,
    },
    class: '_terms_radio',
  },
  {
    label: 'No',
    attributes: {
      type: 'radio',
      name: 'terms',
      id: 'no',
      value: false,
    },
    class: '_terms_radio',
    // validator: (value) => {
    //   if (value) {
    //     return null;
    //   }
    //   return 'you have to agree with terms and conditions';
    // }
  },
  {
    attributes: {
      type: 'submit',
      value: 'Send'
    }
  }
];

const errorEmpty = (name) => `${name} field is empty`;
const errorType = (name, type) => `${name} should be ${type}`;
const errorRange = (name, min, max) => `${name} should be in range from ${min} to ${max}`;

const setFormFields = (fields, form) => {
  for (const field of fields) {
    if (field.label) {
      const label = fieldCreateLabel(field);
      form.appendChild(label);
    }

    if (field.attributes) {
      const elem = fieldCreateInput(field, form);
      form.appendChild(elem);
    }
  }
}

const fieldCreateInput = (field, form) => {
  let elem = document.createElement('input');

  if (field.class) {
    const blockClass = form.classList[0];
    const elemClass = `${blockClass}_${field.class}`;
    elem.classList.add(elemClass);
  }

  elem.form = form;
  inputAssignAttributes(elem, field.attributes);

  return elem;
}

const inputAssignAttributes = (elem, attributes) => {
  for (const key in attributes) {
    elem[key] = attributes[key];
  }
}

const fieldCreateLabel = (field) => {
  const label = document.createElement('label');

  label.innerHTML = field.label;
  label.for = fieldGetFor(field);

  return label;
}

const fieldGetFor = (field) => {
  if (field.id) {
    return field.id;
  }

  if (field.name) {
    return field.name;
  }

  if (!field.attributes) {
    return '';
  }

  if (field.attributes.id) {
    return field.attributes.id;
  }

  if (field.attributes.name) {
    return field.attributes.name;
  }
}

const form = document.getElementById('review');
setFormFields(formFields, form);