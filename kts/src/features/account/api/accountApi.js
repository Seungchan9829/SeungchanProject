import api from "../../../shared/api/axiosInstance";
import { getUserIdByToken } from "../../../shared/jwt/jwtUtils";

export const getAccountInfo = async (accountId) => {
    const { data } = await api.get(`/account/${accountId}`)
    return data
}

export const createAccount = async ({accountNumber, accountPassword}) => {
    // 유저 id가지고 오는 jwt 유틸 함수
    const id = getUserIdByToken();

    const requestDTO = {
        "userId" : id,
        accountNumber,
        accountPassword
    }
    const response = await api.post('/account', requestDTO);

    return response.data;
}
// export const depositAndWithdrawal = (request) => {
//     return api.post(`/account/${accountId}/transaction`)
// }

export const getAccounts = async() => {

}

export const transcationProcess = async({accountId, type, amount }) => {
    const requestDTO = {
        type,
        amount
    }
    const response = await api.post(`/account/${accountId}/transaction`, requestDTO)

    return response.data
}