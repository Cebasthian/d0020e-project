// const url = "http://localhost:19291/public"
// const auth = "eyJraWQiOiJwdWJsaWMta2V5IiwiYWxnIjoiUlMyNTYifQ.eyJpc3MiOiJwcm92aWRlciIsImF1ZCI6ImNvbnN1bWVyIiwic3ViIjoicHJvdmlkZXIiLCJpYXQiOjE3Mzc3MjMzNzg4NjksImp0aSI6IjY0NGY3ZGNmLTNiNjktNDliYS04YjkwLTRiMWRjZjIwODQ5YyJ9.ajRgOaGmpSKJcPp-MHfI4VApmdy6bcFXT9MuvghXvzOg8Aj8gMweHhieiT6i4SzfsoPv11TdIDjMZj4eBp_BkEbo913SXzbty3iolcLoYiYr2lEXX6u7RLcJ4xikBNs9LdPxN3bS_fp-wqEfmLA50RLctPSCQqCEWVzciGsC9CXs_6cLmwvbcYp8C1snBrc5l3tPSDhTpIiJ4LHzHM856vE7oRz1evZEmCDt3vYaxDt2iMyf5kR_FM47MWXARODwFVW0J5YWOcL9Hqa4c5IWoG8dOI1SpNPdtPFiboG0Cu-RwYBFLLCoJE7ebZV6pd9Oi5-MvOstu3Ilw7XgyssCVQ"

// fetch(url, {
//     headers: {
//         "Content-Type": "application/json",
//         "Authorization": auth
//     }
// }).then(res => res.json()).then(data => console.log(data))

// const url = "http://localhost:8083/edc-consumer/transfer/retrieve/c23e3088-e87a-44cb-a7a3-4a7639d0f560"
// fetch(url, {
//     headers: {
//         "Content-Type": "application/json",
//     }
// }).then(res => res.json()).then(data => console.log(data))

// const id = "c23e3088-e87a-44cb-a7a3-4a7639d0f560"
// const url = "http://localhost:29193/management/v3/edrs/" + id + "/dataaddress"
// fetch(url, {
//     headers: {
//         "Content-Type": "application/json",
//     }
// }).then(res => res.json()).then(data => console.log(data))

const url = "http://localhost:12003/management/v3/policydefinitions/request"
fetch(url, {
    headers: {
        "Content-Type": "application/json",
    },
    method: "POST",
    body: JSON.stringify({
        "@context": {
          "@vocab": "https://w3id.org/edc/v0.0.1/ns/"
        },
        "@type": "QuerySpec",
      })
}).then(res => res.json()).then(data => console.log(data))