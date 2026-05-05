import { useEffect, useState, useCallback } from 'react';
import hostApi from '../api/hostApi';
import type { DisplayHostDto } from '../api/types/Host';

interface UseHostReturn {
  host: DisplayHostDto | null;
  loading: boolean;
  error: Error | null;
}

const useHost = (id: number): UseHostReturn => {
  const [host, setHost] = useState<DisplayHostDto | null>(null);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<Error | null>(null);

  const fetch = useCallback(async () => {
    setLoading(true);
    try {
      const response = await hostApi.findById(id);
      setHost(response.data);
      setError(null);
    } catch (err) {
      setError(err instanceof Error ? err : new Error('An unknown error occurred.'));
    } finally {
      setLoading(false);
    }
  }, [id]);

  useEffect(() => {
    void fetch();
  }, [fetch]);

  return { host, loading, error };
};

export default useHost;
