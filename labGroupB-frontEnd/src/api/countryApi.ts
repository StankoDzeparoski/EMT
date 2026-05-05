import axiosInstance from '../axios/axios.ts';
import type { DisplayCountryDto, CreateCountryDto, Page } from './types/Country.ts';

const countryApi = {
  /**
   * Get all countries
   * GET /api/countries
   */
  findAll: async () => {
    return await axiosInstance.get<DisplayCountryDto[]>('/countries');
  },

  /**
   * Get all countries with pagination
   * GET /api/countries/paginated
   */
  findAllPaginated: async (
    page: number = 0,
    size: number = 10,
    sortBy: string = 'name'
  ) => {
    return await axiosInstance.get<Page<DisplayCountryDto>>(
      '/countries/paginated',
      {
        params: {
          page,
          size,
          sortBy
        }
      }
    );
  },

  /**
   * Create a new country
   * POST /api/countries/add
   */
  create: async (createDto: CreateCountryDto) => {
    return await axiosInstance.post<DisplayCountryDto>(
      '/countries/add',
      createDto
    );
  },

  /**
   * Update an existing country
   * PUT /api/countries/{id}/edit
   */
  update: async (id: number, updateDto: CreateCountryDto) => {
    return await axiosInstance.put<DisplayCountryDto>(
      `/countries/${id}/edit`,
      updateDto
    );
  },

  /**
   * Delete a country
   * DELETE /api/countries/{id}/delete
   */
  deleteById: async (id: number) => {
    return await axiosInstance.delete<DisplayCountryDto>(
      `/countries/${id}/delete`
    );
  },

  /**
   * Get country by ID
   * GET /api/countries/{id}
   */
  findById: async (id: number) => {
    return await axiosInstance.get<DisplayCountryDto>(`/countries/${id}`);
  }
};

export default countryApi;
