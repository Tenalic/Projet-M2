import axios from 'axios'
import config from '../config/config'

const API_URL = config.API_BACKEND

export default class Api {
  createUser (user) {
    const url = `${API_URL}/account/create`
    return axios.post(url, user)
  }
}
