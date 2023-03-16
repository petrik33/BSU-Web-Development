// -------------------
// Inputs Creation
// -------------------

const setFields = (fields, formName) => {
  const form = document.forms.namedItem(formName);
  for (const field of fields) {
    const elem = fieldCreateElement(field, form);
    form.appendChild(elem);
  }
}

const formFields = [
  {
    label: 'Enter your name:',
    attributes: {
      type: 'text',
      name: 'name',
      className: 'review_name',
      required: true,
    },
  },
  {
    label: 'Enter your email:',
    attributes: {
      type: 'email',
      name: 'email',
      className: 'review_email',
      required: true
    },
  },
  {
    label: 'Enter rating:',
    attributes: {
      type: 'number',
      name: 'rating',
      className: 'review_rating',
      required: true,
    },
  },
  {
    fieldset: {
      legend: 'Agree with terms and conditions',
      className: 'review__terms',
      fields: [
        {
          label: 'Yes',
          attributes: {
            type: 'radio',
            className: 'review__terms_radio',
            name: 'terms',
            id: 'yes',
            value: true,
          },
        },
        {
          label: 'No',
          attributes: {
            type: 'radio',
            className: 'review__terms_radio',
            name: 'terms',
            id: 'no',
            value: false,
          },
        }
      ]
    }
  },
  {
    attributes: {
      type: 'submit',
      className: 'review_submit',
      value: 'Send'
    }
  }
];

const fieldCreateElement = (field, form) => {
  if (field.fieldset) {
    return fieldCreateFieldset(field.fieldset, form);
  }

  let elem;

  if (field.label) {
    elem = fieldCreateLabel(field);
  }

  if (field.attributes) {
    const input = fieldCreateInput(field);

    if (!elem) {
      return input;
    }

    elem.appendChild(input);
  }

  elem.form = form;

  return elem;
}

const fieldCreateFieldset = (fieldset, form) => {
  const elem = document.createElement('fieldset');

  if (fieldset.legend) {
    const legend = fieldsetCreateLegend(fieldset);
    elem.appendChild(legend);
  }

  fieldsetCreateFields(fieldset.fields, form, elem);

  return elem;
}

const fieldsetCreateFields = (fields, form, container) => {
  for (const field of fields) {
    const elem = fieldCreateElement(field, form);
    container.appendChild(elem);
  }
}

const fieldsetCreateLegend = (fieldset) => {
  const legend = document.createElement('legend');
  legend.innerHTML = fieldset.legend;

  return legend;
}

const fieldCreateInput = (
  field, form, container = form
) => {
  let elem = document.createElement('input');

  if (field.className) {
    const blockclassName = container.classNameList[0];
    const elemclassName = `${blockclassName}_${field.className}`;
    elem.classNameList.add(elemclassName);
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

// -------------------
// Form Setting
// -------------------

setFields(formFields, 'review');

// -------------------
// Validation
// -------------------

const errorsList = document.querySelector('.errors__list');

const errorCreate = (message) => {
  const error = document.createElement('li');
  error.textContent = `Error: ${message}`;
  error.classList.add('errors__list_item');

  return error;
}

const cleanErrorsList = () => {
  while (errorsList.lastElementChild) {
    errorsList.removeChild(errorsList.lastElementChild);
  }
}

const errorEmpty = (
  name
) => `${name} field is empty`;

const errorType = (
  name, type
) => `${name} should be ${type}`;

const errorRange = (
  name, min, max
) => `${name} should be in range from ${min} to ${max}`;

const validateName = (value) => {
  const regex = /^[a-zA-Z ]*$/;
  if (!regex.test(value)) {
    return 'name should only contain latin letters';
  }

  return null;
}

const validateRating = (value) => {
  const min = 1;
  const max = 10;

  if (value < min || value > max) {
    return errorRange('rating', min, max);
  }

  return null;
}

const validateTerms = (value) => {
  if (value == 'false') {
    return 'you have to agree with terms and conditions to submit your review';
  }

  return null;
}

const validator = {
  name: validateName,
  rating: validateRating,
  terms: validateTerms
}

const reviewValidate = () => {
  const form = document.forms.namedItem('review');
  const inputs = form.elements;

  let valid = true;
  cleanErrorsList();

  for (const key in validator) {
    const input = inputs.namedItem(key);

    const errorMessage = validator[key](input.value);

    if (!errorMessage) {
      continue;
    }

    valid = false;

    const error = errorCreate(errorMessage);
    errorsList.appendChild(error);
  }

  return valid;
}