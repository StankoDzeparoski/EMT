import { useEffect, useState, useCallback } from 'react';
import userApi from '../api/userApi';
import type { AuthLogDto } from '../api/types/AuthLog';

const useAuthLogs = () => {
  const [authLogs, setAuthLogs] = useState<AuthLogDto[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<Error | null>(null);

  const fetch = useCallback(async () => {
    setLoading(true);

    try {
      const response = await userApi.getAuthLogs();
      setAuthLogs(response.data);
      setError(null);
    } catch (err) {
      setError(err instanceof Error ? err : new Error('An unknown error occurred.'));
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    void fetch();
  }, [fetch]);

  return { authLogs, loading, error, fetch };
};

export default useAuthLogs;
