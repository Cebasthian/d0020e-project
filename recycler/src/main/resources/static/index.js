const endpoint = (endpoint) => `http://localhost:8081/instructions${endpoint}`

async function getAllEntries() {
    return (await fetch(endpoint("/get"))).json()
}

async function getEntry(id) {
    return (await fetch(endpoint("/get/"+id))).json()
}

async function createEntry(material) {
    return (await fetch(endpoint("/create-entry"), {
        method: "post",
        headers: {
            "Content-Type": "application/json"
        },
        body: material
    })).json()
}

async function updateEntry(entry) {
    return (await fetch(endpoint("/update-entry"), {
        method: "put",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(entry)
    })).json()
}

async function deleteEntry(id) {
    return (await fetch(endpoint("/delete-entry/"+id), {method: "delete"})).ok;
}






const materialInput = document.getElementById("create-material")
async function create() {
    const material = materialInput.value
    if(!material) {
        alert("Material cannot be empty");
        return;
    }
    await createEntry(material)
    materialInput.value = ""
    await refresh()
}
document.getElementById("create-button").addEventListener("click", create)






const entriesDiv = document.getElementById("entries");
async function refresh() {
    const entries = await getAllEntries();
    entriesDiv.innerHTML = ""
    for(let i = 0; i < entries.length; i++) {

        const divider = document.createElement("div")
        divider.className = "divider"
        entriesDiv.append(divider)

        const entry = entries[i]

        const div = document.createElement("div")
        div.className = "entry"

        const h1 = document.createElement("h1")
        h1.innerText = `Material: ${entry.materialId}`
        div.appendChild(h1)

        const inDiv = document.createElement("ol")
        inDiv.className = "entry-instructions"
        const h2 = document.createElement("h2")
        h2.innerText = "Instructions:"
        inDiv.appendChild(h2)

        for(let j = 0; j < entry.instructions.length; j++) {
            const instruction = entry.instructions[j]
            const span = document.createElement("li")
            span.innerText = instruction;
            inDiv.appendChild(span)
        }

        div.appendChild(inDiv)

        const btnDiv = document.createElement("div")
        btnDiv.className = "entry-buttons"

        const del = document.createElement("button")
        del.className = "delete-entry"
        del.innerText = "Delete"
        del.addEventListener("click", async () => {
            await deleteEntry(entry.id)
            await refresh()
        })
        btnDiv.appendChild(del)

        const editBtn = document.createElement("button")
        editBtn.className = "edit"
        editBtn.innerText = "Edit"
        editBtn.addEventListener("click", () => {
            edit(entry)
        })
        btnDiv.appendChild(editBtn)

        const copyButton = document.createElement("button")
        copyButton.className = "copy"
        copyButton.innerText = "Copy Address"
        copyButton.addEventListener("click", async () => {
            navigator.clipboard.writeText(endpoint("/get/"+entry.materialId))
        })
        btnDiv.appendChild(copyButton)

        div.appendChild(btnDiv)

        entriesDiv.appendChild(div)
    }
}
document.getElementById("refresh").addEventListener("click", refresh)
window.addEventListener("load", refresh)




const editContainer = document.getElementById("edit-container")
const materialH1 = document.getElementById("edit-material")
const instructionInput = document.getElementById("add-instruction-input")
const instructionButton = document.getElementById("add-instruction-button")
const saveEditButton = document.getElementById("edit-save")
const instructionsDiv = document.getElementById("edit-instructions")

const editState = {
    entry: undefined
}

function edit(entry) {
    if(!entry) {
        editState.entry = undefined;
        editContainer.hidden = true;
    } else {
        editState.entry = JSON.parse(JSON.stringify(entry));
        editContainer.hidden = false;
    }

    refreshEdit()
}

function refreshEdit() {
    if(!editState.entry) {
        materialH1.innerText = "";
        instructionsDiv.innerHTML = ""
        return;
    };
    
    materialH1.innerText = editState.entry.materialId
    instructionsDiv.innerHTML = ""
    for(let j = 0; j < editState.entry.instructions.length; j++) {
        const instruction = editState.entry.instructions[j]
        const div = document.createElement("div")
        const span = document.createElement("span")
        span.innerText = instruction;
        div.appendChild(span)
        const del = document.createElement("button")
        del.innerText = "Remove"
        del.addEventListener("click", () => {
            const index = editState.entry.instructions.indexOf(instruction)
            editState.entry.instructions.splice(index, 1);
            refreshEdit()
        })
        div.appendChild(del)
        instructionsDiv.appendChild(div)
    }
}

function addInstruction() {
    if(!editState.entry) return;

    const value = instructionInput.value
    if(!value) {
        alert("instruction cannot be empty")
        return;
    }
    editState.entry.instructions.push(value)
    instructionInput.value = ""
    refreshEdit();
}
instructionButton.addEventListener("click", addInstruction)


async function saveEdit() {
    if(!editState.entry) return;

    await updateEntry(editState.entry)
    await refresh();

    edit(undefined)
}
saveEditButton.addEventListener("click", saveEdit)