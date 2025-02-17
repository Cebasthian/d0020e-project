import useSWR from "swr";

// eslint-disable-next-line @typescript-eslint/ban-ts-comment
// @ts-ignore
// const fetcher = (...args) => fetch(...args).then(res => res.json()).catch(err => {throw err})
const fetcher = url => fetch(url).then(r => r.json())

export function useGetter<T>(url: string, fallbackData: T) {
    const obj = useSWR<T>(url, fetcher, {fallbackData: fallbackData})
    const {data, mutate, error, isLoading} = obj

    const refresh = () => mutate();

    if(isLoading) {
        return {data: fallbackData, refresh, isLoading}
    }

    if(error) {
        return {data: fallbackData, refresh, error}
    }

    // eslint-disable-next-line @typescript-eslint/ban-ts-comment
    // @ts-ignore
    if(data && Object.hasOwn(data, "status") && data.status > 299) {
        return {
            data: fallbackData,
            error: data,
            refresh
        }
    }

    return {
        data: data || fallbackData,
        refresh
    }
}