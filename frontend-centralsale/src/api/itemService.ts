import axiosInstance from '../util/axios';
import type {UserProductDTO, UserProductCreateDTO} from '../types/item'

export const itemService = {

  createItem: async (userId: string, url: string): Promise<UserProductDTO> => {
    const response = await axiosInstance.post<UserProductDTO>('/users/products', {
      userId,
      url,
    } as UserProductCreateDTO);
    return response.data;
  },

  fetchItems: async (userId: string): Promise<UserProductDTO[]> => {
    const response = await axiosInstance.get<UserProductDTO[]>(`/users/products/${userId}`);
    return response.data;
  }

}