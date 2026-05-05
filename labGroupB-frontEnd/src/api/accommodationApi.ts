import axiosInstance from '../axios/axios.ts';
import type {
  DisplayAccommodationDto,
  CreateAccommodationDto,
  AccommodationProjection,
  AccommodationExtendedProjection,
  OptimizedAccommodationDto,
  Page,
  Category
} from './types/Accommodation.ts';

const accommodationApi = {
  /**
   * Get accommodation by ID
   * GET /api/accommodations/{id}
   */
  findById: async (id: number) => {
    return await axiosInstance.get<DisplayAccommodationDto>(
      `/accommodations/${id}`
    );
  },

  /**
   * Add a review to an accommodation
   * POST /api/accommodations/{id}/add-review
   */
  addReview: async (id: number, comment: string, rating: number) => {
    return await axiosInstance.post<void>(
      `/accommodations/${id}/add-review`,
      {},
      {
        params: {
          comment,
          rating
        }
      }
    );
  },

  /**
   * Get all accommodations
   * GET /api/accommodations
   */
  findAll: async () => {
    return await axiosInstance.get<DisplayAccommodationDto[]>(
      '/accommodations'
    );
  },

  /**
   * Get all accommodations with pagination
   * GET /api/accommodations/paginated
   */
  findAllPaginated: async (
    page: number = 0,
    size: number = 10,
    sortBy: string = 'name'
  ) => {
    return await axiosInstance.get<Page<DisplayAccommodationDto>>(
      '/accommodations/paginated',
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
   * Search accommodations with filters
   * GET /api/accommodations/search
   */
  findAllWithFilters: async (
    page: number = 0,
    size: number = 10,
    sortBy: string = 'name',
    category?: Category | string,
    hostId?: number,
    countryId?: number,
    numRooms?: number,
    hasAvailableRooms?: boolean
  ) => {
    const params: Record<string, any> = {
      page,
      size,
      sortBy
    };

    if (category !== undefined) params.category = category;
    if (hostId !== undefined) params.hostId = hostId;
    if (countryId !== undefined) params.countryId = countryId;
    if (numRooms !== undefined) params.numRooms = numRooms;
    if (hasAvailableRooms !== undefined) params.hasAvailableRooms = hasAvailableRooms;

    return await axiosInstance.get<Page<DisplayAccommodationDto>>(
      '/accommodations/search',
      { params }
    );
  },

  /**
   * Get all accommodations as projections (lightweight)
   * GET /api/accommodations/projection
   */
  findAllProjection: async (
    page: number = 0,
    size: number = 10
  ) => {
    return await axiosInstance.get<Page<AccommodationProjection>>(
      '/accommodations/projection',
      {
        params: {
          page,
          size
        }
      }
    );
  },

  /**
   * Get all accommodations as extended projections
   * GET /api/accommodations/projectionExtended
   */
  findAllExtendedProjection: async (
    page: number = 0,
    size: number = 10
  ) => {
    return await axiosInstance.get<Page<AccommodationExtendedProjection>>(
      '/accommodations/projectionExtended',
      {
        params: {
          page,
          size
        }
      }
    );
  },

  /**
   * Get all accommodations with host loaded (optimized)
   * Avoids N+1 query problem - single query with JOIN to hosts table
   * GET /api/accommodations/optimized/with-host
   */
  findAllWithHost: async () => {
    return await axiosInstance.get<OptimizedAccommodationDto[]>(
      '/accommodations/optimized/with-host'
    );
  },

  /**
   * Get all accommodations with host and country loaded (optimized)
   * Avoids N+1 query problem - single query with JOINs to hosts and countries tables
   * GET /api/accommodations/optimized/with-host-and-country
   */
  findAllWithHostAndCountry: async () => {
    return await axiosInstance.get<OptimizedAccommodationDto[]>(
      '/accommodations/optimized/with-host-and-country'
    );
  },

  /**
   * Create a new accommodation
   * POST /api/accommodations/add
   */
  create: async (createDto: CreateAccommodationDto) => {
    return await axiosInstance.post<DisplayAccommodationDto>(
      '/accommodations/add',
      createDto
    );
  },

  /**
   * Update an existing accommodation
   * PUT /api/accommodations/{id}/edit
   */
  update: async (id: number, updateDto: CreateAccommodationDto) => {
    return await axiosInstance.put<DisplayAccommodationDto>(
      `/accommodations/${id}/edit`,
      updateDto
    );
  },

  /**
   * Delete an accommodation
   * DELETE /api/accommodations/{id}/delete
   */
  deleteById: async (id: number) => {
    return await axiosInstance.delete<DisplayAccommodationDto>(
      `/accommodations/${id}/delete`
    );
  },

  /**
   * Rent an accommodation
   * Marks the accommodation as occupied and publishes an AccommodationRentedEvent
   * POST /api/accommodations/{id}/rent
   */
  rentAccommodation: async (id: number) => {
    return await axiosInstance.post<DisplayAccommodationDto>(
      `/accommodations/${id}/rent`
    );
  }
};

export default accommodationApi;
