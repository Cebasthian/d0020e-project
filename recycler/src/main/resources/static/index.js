function url(endpoint) {
    return `/instructions${endpoint}`
}


async function getAll() {
    const res = await fetch(url("/get-entries"))
    const json = await res.json();
    return json;
}

async function create(material) {
    const res = await fetch(url("/create-entry"), {
        method: "POST",
        body: material
    })
    const json = await res.json();
    return json;
}

async function update(entry) {
    const res = await fetch(url("/update-entry"), {
        method: "PUT",
        body: JSON.stringify(entry),
        headers: {
            "Content-Type": "application/json"
        }
    })
    const json = await res.json();
    return json;
}