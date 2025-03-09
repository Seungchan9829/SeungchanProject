import api from "../../../shared/api/axiosInstance";

export const getAccountInfo = (accountId) => {
    return api.get(`/account/${accountId}`)
}

// export const depositAndWithdrawal = (request) => {
//     return api.post(`/account/${accountId}/transaction`)
// }