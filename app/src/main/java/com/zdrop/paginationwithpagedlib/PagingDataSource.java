package com.zdrop.paginationwithpagedlib;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import androidx.paging.PagingSource;
import androidx.paging.PagingState;
import kotlin.coroutines.Continuation;

public class PagingDataSource extends PagingSource<Integer, Object> {

    private final PaginationListener paginationListener;

    public PagingDataSource(PaginationListener paginationListener){
        this.paginationListener = paginationListener;
    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NotNull PagingState<Integer, Object> pagingState) {
        Integer anchorPosition = pagingState.getAnchorPosition();
        if (anchorPosition == null) {
            return null;
        }

        LoadResult.Page<Integer, ?> anchorPage = pagingState.closestPageToPosition(anchorPosition);
        if (anchorPage == null) {
            return null;
        }

        Integer prevKey = anchorPage.getPrevKey();
        if (prevKey != null) {
            return prevKey + 1;
        }

        Integer nextKey = anchorPage.getNextKey();
        if (nextKey != null) {
            return nextKey - 1;
        }

        return null;
    }

    @Nullable
    @Override
    public Object load(@NotNull LoadParams<Integer> loadParams
            , @NotNull Continuation<? super LoadResult<Integer, Object>> continuation) {
        return (paginationListener == null) ? null : paginationListener.onLoadMore(loadParams.getKey());
    }

    public interface PaginationListener{
        LoadResult.Page<Integer, ?> onLoadMore(Integer currentPage);
    }
}
