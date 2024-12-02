async function fetchUserData(url) {
    try {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error('Network error: ' + response.status);
        }
        const user = await response.json();
        console.log(response)
        displayUserData(user);
    } catch (error) {
        console.error('Error', error);
        document.getElementById('user-table-body').innerHTML = '<tr><td colspan="4" class="text-danger">Error</td></tr>';
    }
}

function displayUserData(user) {
    const usernameElement = document.getElementById('username');
    usernameElement.textContent = user.username || 'Не указано';

    const rolesElement = document.getElementById('roles');
    rolesElement.textContent = user.roles ? user.roles.map(role => role.roleName).join(', ') : 'Не указано';

    const userTableBody = document.getElementById('user-table-body');

    userTableBody.innerHTML = `<tr>
                <td>${user.id || 'Не указано'}</td>
                <td>${user.username || 'Не указано'}</td>
                <td>${user.email || 'Не указано'}</td>
                <td>${user.age || 'Не указано'}</td>
                <td>${user.sex || 'Не указано'}</td>
                <td>${user.phoneNumber || 'Не указано'}</td>
                <td>${user.roles ? user.roles.map(role => role.roleName).join(', ') : 'Не указано'}</td>
            </tr>`;
    console.log(user)
}

(async () => {
    await fetchUserData("/api/user");
})();
