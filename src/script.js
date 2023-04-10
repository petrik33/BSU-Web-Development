const homePageHtml = `
  <div>
    <h2>Welcome to Atom Playground!</h2>
    <p>Atom Playground is a fun and interactive online platform for kids to learn about the amazing world of atoms and molecules. Our tools and simulations make it easy for kids to explore and discover the building blocks of matter.</p>
    <p>Whether you're a curious kid or a parent looking for a fun and educational activity, Atom Playground is the perfect place to learn and play.</p>
    <h3>Our Features</h3>
    <ul>
      <li>Engaging simulations and games that make learning about atoms and molecules fun</li>
      <li>Interactive tutorials that guide kids through the basics of chemistry</li>
      <li>A safe and friendly community of young learners and expert educators</li>
      <li>And much more!</li>
    </ul>
    <p>Join Atom Playground today and start your journey into the amazing world of atoms and molecules!</p>
  </div>
`;

const subjectPageHtml = `
  <div>
    <h2>Visitors</h2>
    <table>
      <thead>
        <tr>
          <th>Login</th>
          <th>Name</th>
        </tr>
      </thead>
      <tbody id="table-body">
      </tbody>
    </table>
  </div>
`;

const loadData = (data) => {
  for (key in data) {
    const row = $('<tr>');
    const loginCell = $('<td>').text(key);
    const nameCell = $('<td>').text(data[key]);
    row.append(loginCell);
    row.append(nameCell);
    $('#table-body').append(row);
  }
}

const subjectPageOnLoad = () => {
  $.ajax({
    url: '/data/visitors.json',
    dataType: 'json',
    success: loadData,
    error: function (jqXHR, textStatus, errorThrown) {
      console.error(textStatus, errorThrown);
    }
  });
}

const changePage = (page) => {
  $('#current-page').html(page.html);
  if (page.onload) {
    page.onload();
  }
}

const storeCurrentPage = () => {
  localStorage.setItem('currentPage', window.location.hash);
}

const loadCurrentPage = () => {
  return localStorage.getItem('currentPage');
}

const changeUrlHash = (hash) => {
  location.hash = hash;
}

const hashChangeHandler = () => {
  let UrlHash = window.location.hash;
  if (!UrlHash) {
    UrlHash = loadCurrentPage();
    if (!UrlHash) {
      UrlHash = "#Home";
    }
  }
  const pageKey = UrlHash.substring(1);
  changePage(pagesHash[pageKey]);
  storeCurrentPage();
}

const makePagesNavItems = (pages) => {
  for (const key in pages) {
    const page = pages[key];
    const item = $('<li>');
    const anchor = $(`<a href=#${key}>${page.name}</a>`);
    item.append(anchor);
    $('#pages-list').append(item);
  }
}

const pagesHash = {
  "Home": {
    name: "Home",
    html: homePageHtml
  },
  "Subject": {
    name: "Visitors",
    html: subjectPageHtml,
    onload: subjectPageOnLoad
  }
}

window.onhashchange = hashChangeHandler;
makePagesNavItems(pagesHash);
hashChangeHandler();
