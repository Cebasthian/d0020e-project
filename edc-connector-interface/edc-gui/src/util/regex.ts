export function isUrl(url: string) {
    return (/^https?:\/\//g).test(url)
}