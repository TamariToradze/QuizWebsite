document.addEventListener('DOMContentLoaded', function () {
  console.log('DOM fully loaded and parsed');
  const searchInput = document.getElementById('search-input');
  const suggestionsContainer = document.getElementById('suggestions');
  const searchForm = document.getElementById('search-form');

  if (searchInput) {
    console.log('searchInput found');
    searchInput.addEventListener('input', function () {
      const query = searchInput.value;
      if (query.length > 2) { // Only fetch suggestions if the query is longer than 2 characters
        fetch(`/suggestion?query=${encodeURIComponent(query)}`)
            .then(response => response.json())
            .then(data => {
              suggestionsContainer.innerHTML = ''; // Clear previous suggestions
              if (data.length > 0) {
                const ul = document.createElement('ul');
                data.forEach(item => {
                  const li = document.createElement('li');
                  li.textContent = `${item.firstName} ${item.lastName} (${item.userName})`; // Adjust based on the returned data structure
                  li.addEventListener('click', () => {
                    searchInput.value = `${item.firstName} ${item.lastName}`;
                    suggestionsContainer.innerHTML = '';
                    searchForm.submit(); // Submit the form with selected suggestion
                  });
                  ul.appendChild(li);
                });
                suggestionsContainer.appendChild(ul);
                suggestionsContainer.style.display = 'block';
              } else {
                suggestionsContainer.style.display = 'none';
              }
            })
            .catch(error => console.error('Error fetching suggestions:', error));
      } else {
        suggestionsContainer.style.display = 'none';
      }
    });
  } else {
    console.error('searchInput is null');
  }
});