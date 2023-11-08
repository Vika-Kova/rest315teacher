async function GetOneUser(id) {
    let url = "adminApi/user/" + id;
    let response = await fetch(url);
    return await response.json();
}