import axiosInstance from '../axios/axios.ts';
import type { DisplayHostDto, CreateHostDto, Page } from './types/Host.ts';

const hostApi = {
  /**
   * Get all hosts
   * GET /api/hosts
   */
  findAll: async () => {
    return await axiosInstance.get<DisplayHostDto[]>('/hosts');
  },

  /**
   * Get all hosts with pagination
   * GET /api/hosts/paginated
   */
  findAllPaginated: async (
    page: number = 0,
    size: number = 10,
    sortBy: string = 'name'
  ) => {
    return await axiosInstance.get<Page<DisplayHostDto>>('/hosts/paginated', {
      params: {
        page,
        size,
        sortBy
      }
    });
  },

  /**
   * Create a new host
   * POST /api/hosts/add
   */
  create: async (createDto: CreateHostDto) => {
    return await axiosInstance.post<DisplayHostDto>('/hosts/add', createDto);
  },

  /**
   * Update an existing host
   * PUT /api/hosts/{id}/edit
   */
  update: async (id: number, updateDto: CreateHostDto) => {
    return await axiosInstance.put<DisplayHostDto>(
      `/hosts/${id}/edit`,
      updateDto
    );
  },

  /**
   * Delete a host
   * DELETE /api/hosts/{id}/delete
   */
  deleteById: async (id: number) => {
    return await axiosInstance.delete<DisplayHostDto>(
      `/hosts/${id}/delete`
    );
  },

  /**
   * Get host by ID
   * GET /api/hosts/{id}
   */
  findById: async (id: number) => {
    return await axiosInstance.get<DisplayHostDto>(`/hosts/${id}`);
  }
};

export default hostApi;
