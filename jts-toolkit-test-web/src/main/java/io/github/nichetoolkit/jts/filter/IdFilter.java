package io.github.nichetoolkit.jts.filter;

import com.fasterxml.jackson.annotation.JsonSetter;
import io.github.nichetoolkit.rest.util.common.GeneralUtils;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.*;

/**
 * <p>IdFilter</p>
 * @author Cyan (snow22314@outlook.com)
 * @version V.0.0.1
 * @group cyan.tool.kit
 * @date 17:39 2020/9/8
 */
public class IdFilter<I> implements Serializable {

    protected I id;

    protected Set<I> ids;

    public IdFilter() {
    }

    public IdFilter(I id) {
        this.id = id;
    }

    /**
     * the generic paradigm of T is not Like List<T>、Set<T>
     * It should be String、Long、Integer ...
     * @param ids
     */
    @SuppressWarnings(value = "unchecked")
    public IdFilter(@NonNull I... ids) {
        this.ids = new HashSet<>(Arrays.asList(ids));
    }

    public IdFilter(@NonNull Collection<I> ids) {
        this.ids = new HashSet<>(ids);
    }

    public IdFilter(IdFilter.Builder<I> builder) {
        this.id = builder.id;
        this.ids = builder.ids;
    }


    public I getId() {
        return id;
    }

    public void setId(I id) {
        this.id = id;
    }

    public List<I> getIds() {
        if (GeneralUtils.isNotEmpty(ids)) {
            return new ArrayList<>(ids);
        }
        return Collections.emptyList();
    }

    @JsonSetter
    public void setIds(@NonNull Collection<I> ids) {
        this.ids = new HashSet<>(ids);
    }

    @SuppressWarnings(value = "unchecked")
    public void setIds(@NonNull I... ids) {
        this.ids = new HashSet<>(Arrays.asList(ids));
    }

    @SuppressWarnings(value = "unchecked")
    public void addIds(@NonNull I... ids) {
        if (GeneralUtils.isEmpty(this.ids)) {
            this.ids = new HashSet<>(Arrays.asList(ids));
        } else {
            this.ids.addAll(Arrays.asList(ids));
        }
    }

    public void addIds(@NonNull Collection<I> ids) {
        if (GeneralUtils.isEmpty(this.ids)) {
            this.ids = new HashSet<>(ids);
        } else {
            this.ids.addAll(ids);
        }
    }

    public static class Builder<I> {
        protected I id;
        protected Set<I> ids;

        public Builder() {
        }

        public IdFilter.Builder<I> id(I id) {
            this.id = id;
            return this;
        }

        public IdFilter.Builder<I> ids(@NonNull Collection<I> ids) {
            this.ids = new HashSet<>(ids);
            return this;
        }

        @SuppressWarnings(value = "unchecked")
        public IdFilter.Builder<I> ids(@NonNull I... ids) {
            this.ids = new HashSet<>(Arrays.asList(ids));
            return this;
        }

        public IdFilter<I> build() {
            return new IdFilter<>(this);
        }
    }
}
